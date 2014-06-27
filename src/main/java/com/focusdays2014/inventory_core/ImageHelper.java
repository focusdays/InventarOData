package com.focusdays2014.inventory_core;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.util.log.Log;
import org.odata4j.format.json.JsonWriter;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ImageHelper {
 
	final static String IMAGE_UPLOAD_URL = "http://images.google.ch/searchbyimage?image_url=";
	static WebClient WEB_CLIENT;
	
	private String fileName;
	private transient HtmlPage htmlPage;
	
	
	public ImageHelper(String fileName) {
		this.fileName = fileName;
	}


	public String getImagesJson() throws IOException {
		try {
			List<String> similarImagesMeta = getSimilarImages();
			StringWriter writer = new StringWriter();
			JsonWriter w = new JsonWriter(writer);
			w.startObject();
			w.writeName("image"); w.writeString(this.getFileName());
			w.writeSeparator();
			w.writeName("keywords"); w.writeString(this.getKeywords());
			w.writeSeparator();
			w.writeName("similarImages");
				w.startArray();
				boolean start = true;
				for (String element : similarImagesMeta) {
					if (!start) { w.writeSeparator(); } else { start = false; }
					w.writeRaw(element);
				}
				w.endArray();
			w.endObject();
			return writer.toString();
		} finally {
			if (this.htmlPage != null) {
				this.htmlPage.cleanUp();
			}
		}
	}

	private List<String> getSimilarImages() throws IOException {
		return this.findTags("div", "class", "rg_meta");
	}
	private String getKeywords() throws IOException {
		for (String s : this.findTags("a", "class", "qb-b")) {
			return s;
		}
		return "none";
	}

	private List<String> findTags(String tagName, String attrName, String value) throws IOException {
		HtmlPage htmlPage = this.getHTML();
		DomNodeList<DomElement> elementByName = htmlPage.getElementsByTagName(tagName);
		List<String> similarStrings = new ArrayList<String>();
		for (DomElement domElement : elementByName) {
			if (value.equals(domElement.getAttribute(attrName))) {
				similarStrings.add(domElement.asText());
			}
		}
		return similarStrings;
	}



	private HtmlPage getHTML() throws IOException {
		if (this.htmlPage == null) {
			String url = IMAGE_UPLOAD_URL + URLEncoder.encode(this.getFileName());
			Log.getLogger(this.getClass()).info("Google image search query", url);
			this.htmlPage = getWebClient().getPage(url);
			Log.getLogger(this.getClass()).info("Google image search response ", this.htmlPage.getUrl());
		}
		return this.htmlPage;
	}


	private static synchronized WebClient getWebClient() {
		if (WEB_CLIENT == null) {
			WebClient webClient = new WebClient();
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getBrowserVersion().setUserAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
			webClient.getBrowserVersion().setBrowserLanguage("en");
			webClient.getBrowserVersion().setUserLanguage("en");
			WEB_CLIENT = webClient;
		}
		return WEB_CLIENT;
	}
	
	
	public String getFileName() {
		return fileName;
	}



}

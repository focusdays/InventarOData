package com.focusdays2014.inventory_core;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.odata4j.format.json.JsonWriter;

public class ImageHelper {

	final static String IMAGE_UPLOAD_URL = "http://images.google.ch/searchbyimage?image_url=";
	
	private String fileName;
	private transient Document html;
	
	public ImageHelper(String fileName) {
		this.fileName = fileName;
	}

	public String detectKeywords() throws IOException {
		Document html = this.getHTML();
		Elements keywords = html.select(".qb-b");
		if (keywords != null) return keywords.text();
		return "";
	}

	public String getImagesJson() throws IOException {
		List<Element> similarImagesMeta = getSimilarImages();
		StringWriter writer = new StringWriter();
		JsonWriter w = new JsonWriter(writer);
		w.startObject();
		w.writeName("image"); w.writeString(this.getFileName());
		w.writeSeparator();
		w.writeName("keywords"); w.writeString(this.detectKeywords());
		w.writeSeparator();
		w.writeName("similarImages");
			w.startArray();
			boolean start = true;
			for (Element element : similarImagesMeta) {
				if (!start) { w.writeSeparator(); } else { start = false; }
				w.writeRaw(element.text());
			}
			w.endArray();
		w.endObject();
		return writer.toString();
	}

	private List<Element> getSimilarImages() throws IOException {
		Document html = this.getHTML();
		List<Element> similarImagesMeta = html.select(".rg_meta");
		return similarImagesMeta;
	}

	@SuppressWarnings("deprecation")
	private Document getHTML() throws IOException {
		if (this.html == null) {
//			Connection connect = Jsoup.connect(IMAGE_UPLOAD_URL + URLEncoder.encode(this.getFileName()));
			Connection connect = Jsoup.connect("http://inventory42-focusdays2014.rhcloud.com/UploadServlet?name=cc3513b5-cb0a-4a55-9db5-4f775ac63578.jpeg");
			connect.followRedirects(true);
			this.html = connect.get();
		}
		return this.html;
	}

	public String getFileName() {
		return fileName;
	}



}

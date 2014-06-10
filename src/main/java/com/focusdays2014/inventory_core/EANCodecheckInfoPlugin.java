package com.focusdays2014.inventory_core;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EANCodecheckInfoPlugin implements EANTitlePlugin {

	private static final String IMAGE_URL = "http://www.codecheck.info/product.search?q=";

	private String code;

	private String format;

	@Override
	public void setEAN(String code) {
		this.code = code;
	}

	@Override
	public String getEAN() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getEANTitle() throws IOException {
		Document html = this.getHTML(this.getEAN());
		Elements elementsH1 = html.getElementsByAttributeValueStarting("class", "h1Title");
		for (Element element : elementsH1) {
			return element.text();
		}
		throw new IOException("EAN code not found");
	}

	private Document getHTML(String imageURL) throws IOException {
		if (imageURL == null)
			throw new IOException("URL query string not defined");

		return Jsoup
				.connect(IMAGE_URL + URLEncoder.encode(imageURL))
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36")
				.header("Accept", "text/html")
				.header("Accept-Language", "en-US")
				.header("Cache-Control", "max-age=0")
				.header("Accept-Encoding", "gzip,deflate").get();
	}

	@Override
	public String getFormat() {
		return this.format;
	}

	@Override
	public void setFormat(String format) {
		this.format = format;
		
	}
	
	
}

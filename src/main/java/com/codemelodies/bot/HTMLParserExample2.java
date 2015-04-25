package com.codemelodies.bot;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParserExample2 {

	public static void main(String[] args) {

		Document doc2;
		try {

			// get all images
			doc2 = Jsoup.connect("http://yahoo.com").userAgent("Mozilla").get();
			Elements images = doc2.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			for (Element image : images) {

				System.out.println("\nsrc : " + image.attr("src"));
				System.out.println("height : " + image.attr("height"));
				System.out.println("width : " + image.attr("width"));
				System.out.println("alt : " + image.attr("alt"));
 
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
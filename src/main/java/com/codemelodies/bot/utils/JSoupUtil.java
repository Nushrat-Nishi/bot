package com.codemelodies.bot.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupUtil {
	// ********************getTagAttListFromPage***************************************
	public static List<String> getTagAttListFromPage(String pageLink,
			String selectTag, String tagAtt) {
		Document doc = null;

		List<String> linksAllFromSite = new ArrayList<String>();

		try {
			doc = Jsoup.connect(pageLink).userAgent("Mozilla").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements links = doc.select(selectTag);

		for (Element link : links) {
			linksAllFromSite.add(link.attr(tagAtt));
		}
		return linksAllFromSite;

	}


}

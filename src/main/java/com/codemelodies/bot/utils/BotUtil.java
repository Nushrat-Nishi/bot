package com.codemelodies.bot.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BotUtil {
	// ***************getUserFavouriteLinks********************************************
	public static String getUserFavouriteLinks(String userProfile) {

		String fvrtArtclsStrPage = userProfile.concat("/favourites");

		return fvrtArtclsStrPage;
	}

	// *********************getUserProfileLinks**************************************
	public static Set<String> getUserProfileLinks(
			List<String> linksAllFromSite, String linkGiven) {
		Set<String> profileLinks = new HashSet<String>();

		for (String link : linksAllFromSite) {

			if (link.startsWith(linkGiven)) {
				String[] splitStrings = link.split("/");
				String[] splitStringslinkGiven = linkGiven.split("/");
				if (splitStrings.length == splitStringslinkGiven.length + 1) {
					profileLinks.add(link);
				}
			}
		}
		return profileLinks;
	}

	// ****************getfavrtLinkWhoHaveset*******************************************
	public static boolean isFavouriteArticleExist(String favouriteURL) {
		boolean validity = false;
		Document doc = null;

		try {
			doc = Jsoup.connect(favouriteURL).userAgent("Mozilla").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements allLink = doc.select("a[href]");
		for (Element checkLink : allLink) {
			if (checkLink.attr("title").endsWith("টি মন্তব্য")) {
				validity = true;
				break;
			}
			validity = false;
		}
		return validity;
	}

	// ***********************************************************
	public static String getMainLink(String linkArticleComment) {
		String linkArticle = linkArticleComment.substring(0,
				linkArticleComment.length() - 9);
		return linkArticle;
	}

	public static int getMainCommentCount(String commentCountString) {

		String commentCountBangla = commentCountString.substring(0,
				commentCountString.length() - 10);

		String commentCountEnglish = StringUtil
				.banglaToEnglish(commentCountBangla);

		int commentCountInteger = Integer.parseInt(commentCountEnglish);

		return commentCountInteger;
	}

	public static int getMainViewCount(String viewCountString) {

		String viewCountBangla = viewCountString.substring(0,
				viewCountString.length() - 8);

		String viewCountEnglish = StringUtil.banglaToEnglish(viewCountBangla);

		int viewCountInteger = Integer.parseInt(viewCountEnglish);

		return viewCountInteger;
	}

	public static int getMainLikeCount(String likeCountString) {

		String likeCountBangla = likeCountString.substring(
				likeCountString.length() - 18, likeCountString.length() - 17);

		String likeCountEnglish = StringUtil.banglaToEnglish(likeCountBangla);

		int likeCountInteger = Integer.parseInt(likeCountEnglish);

		return likeCountInteger;
	}

	//********************getparagraphs***************************************
	public static Elements getparagraphs(String favouriteURL) {
		Document doc = null;

		try {
			doc = Jsoup.connect(favouriteURL).userAgent("Mozilla").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements paragraphs = doc.select("div#rightpanel p.st_end");

		return paragraphs;
	}
}

package com.codemelodies.bot;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.codemelodies.bot.utils.BotUtil;
import com.codemelodies.dto.ArticleSummery;

public class ArticleSummeryList {
	public static List<ArticleSummery> getArticleSummeryList(Elements paragraphs) {

		List<ArticleSummery> articleSummeryList = new ArrayList<ArticleSummery>();
		for (Element paragraph : paragraphs) {
			ArticleSummery articleSummery = new ArticleSummery();

			Elements anchors = paragraph.select("a");
			Elements spans = paragraph.select("span");

			for (Element anchor : anchors) {
				if (anchor.attr("title").endsWith("টি মন্তব্য")) {
					String linkArticleComment = anchor.attr("href");
					String linkArticle = BotUtil
							.getMainLink(linkArticleComment);

					articleSummery.setLinkArticle(linkArticle);

					String commentCountString = anchor.attr("title");

					int commentCount = BotUtil
							.getMainCommentCount(commentCountString);
					articleSummery.setCommentCount(commentCount);

				}
			}

			for (Element spanTag : spans) {

				if (spanTag.attr("title").endsWith("বার পঠিত")) {
					String viewCountString = spanTag.attr("title");
					int viewCount = BotUtil.getMainViewCount(viewCountString);
					articleSummery.setViewCount(viewCount);

				} else if (spanTag.attr("title").endsWith("জনের ভাল লেগেছে")) {
					String likeCountString = spanTag.attr("title");
					int likeCount = BotUtil.getMainLikeCount(likeCountString);
					articleSummery.setLikeCount(likeCount);
				}
			}
			articleSummeryList.add(articleSummery);
		}
		return articleSummeryList;
	}
}

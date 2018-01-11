package com.codemelodies.bot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.select.Elements;

import com.codemelodies.bot.utils.BotUtil;
import com.codemelodies.bot.utils.HibernateUtil;
import com.codemelodies.bot.utils.JSoupUtil;
import com.codemelodies.dto.ArticleSummery;

public class SomeWhereInBlogBot {

	public static void main(String[] args) {

		List<String> linksAllFromHomeBody = new ArrayList<String>();
		List<String> linksAllFromHomeSide = new ArrayList<String>();

		/*********************************************************************/

		String pageLink1 = "http://somewhereinblog.net";
		String selectTag1 = "a[href]";
		String tagAtt1 = "href";
		linksAllFromHomeBody = JSoupUtil.getTagAttListFromPage(pageLink1,
				selectTag1, tagAtt1);

		String pageLink2 = "http://www.somewhereinblog.net/action/onlinevisitor";
		String selectTag2 = "a[href]";
		String tagAtt2 = "href";
		linksAllFromHomeSide = JSoupUtil.getTagAttListFromPage(pageLink2,
				selectTag2, tagAtt2);
		// *************************************

		// UserProfileLinkSet userProfileLinkSet = new UserProfileLinkSet();

		String linkGiven = "http://www.somewhereinblog.net/blog/";

		Set<String> userProfileSetHome = new HashSet<String>();
		userProfileSetHome = BotUtil.getUserProfileLinks(linksAllFromHomeBody,
				linkGiven);

		Set<String> userProfileSetSideBar = new HashSet<String>();
		userProfileSetSideBar = BotUtil.getUserProfileLinks(
				linksAllFromHomeSide, linkGiven);

		userProfileSetHome.addAll(userProfileSetSideBar);

		System.out.println("\nThe size of the resultant User set is: "
				+ userProfileSetHome.size());

		List<ArticleSummery> articleSummeryList = new ArrayList<ArticleSummery>();

		int i = 0;
		for (String userProfile : userProfileSetHome) {
			i += 1;
			System.out.println("\n(" + i + ")The Profile Link Of The User: "
					+ userProfile);
			String favouriteURL = BotUtil.getUserFavouriteLinks(userProfile);

			System.out
					.println("The Favourite Links Container Page Of This User: "
							+ favouriteURL);
			System.out
					.println("Show The Favourite Article's List & their Properties: ");
			if (BotUtil.isFavouriteArticleExist(favouriteURL) == true) {

				Elements paragraphs = BotUtil.getparagraphs(favouriteURL);
				articleSummeryList = ArticleSummeryList
						.getArticleSummeryList(paragraphs);
				System.out.println(articleSummeryList);

			} else {
				System.out
						.println("This User Doesn't Have Any Favourite List.");
			}

			// HACK
			//break;

		}

		/***************** perfect ei porjonto ***************/
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		for (ArticleSummery articleSummery : articleSummeryList) {
			session.save(articleSummery);

			
		}
		session.getTransaction().commit();

		Query q = session.createSQLQuery("select * from ArticleSummery")
				.addEntity(ArticleSummery.class);

		@SuppressWarnings("unchecked")
		List<ArticleSummery> resultList = q.list();

		System.out.println("Number of article summery:" + resultList.size());
		for (ArticleSummery next : resultList) {
			System.out.println("next employee: " + next);
		}
		
		/*****************************************************/
	}
}

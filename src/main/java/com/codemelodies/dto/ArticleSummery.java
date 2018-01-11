package com.codemelodies.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ArticleSummery {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "link", unique = true)
	private String linkArticle;

	private int commentCount;
	private int viewCount;
	private int likeCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLinkArticle() {
		return linkArticle;
	}

	public void setLinkArticle(String linkArticle) {
		this.linkArticle = linkArticle;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "\nArticleSummery [linkArticle=" + linkArticle
				+ ", commentCount=" + commentCount + ", viewCount=" + viewCount
				+ ", likeCount=" + likeCount + "]";
	}

}

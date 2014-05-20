package com.myblog.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BlogPost extends AbstractDocument {

	private String title;
	
	private String content;
	
	private Date publishedTimeStamp;
	
	private List<String> tags;
	
	public BlogPost() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishedTimeStamp() {
		return publishedTimeStamp;
	}

	public void setPublishedTimeStamp(Date publishedTimeStamp) {
		this.publishedTimeStamp = publishedTimeStamp;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}


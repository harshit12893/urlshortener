package com.url.urlshortener.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlResponseBean {
	private String shortUrl;
	
	@JsonCreator
	public UrlResponseBean(@JsonProperty("shortUrl") String shortUrl) {
		
		this.shortUrl = shortUrl;
	}
	
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
}

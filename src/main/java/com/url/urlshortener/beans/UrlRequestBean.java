package com.url.urlshortener.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlRequestBean {
	
	private String originalUrl;
	

	public UrlRequestBean() {
		
	}
	@JsonCreator
	public UrlRequestBean(@JsonProperty("originalUrl") String originalUrl) {
		
		this.originalUrl = originalUrl;
	}

	public String getOrginalUrl() {
		return originalUrl;
	}

	public void setOrginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	
	
}
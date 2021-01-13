package com.url.urlshortener.service;

import com.url.urlshortener.beans.UrlRequestBean;

public interface UrlShortenerService {
	public String shortenUrl(UrlRequestBean urlRequestBean,String localUrl);
	public String getLongUrlFromIdHash(String idHash); 
}


package com.url.urlshortener.dao;

import com.url.urlshortener.model.UrlsData;

public interface UrlShortenerDao {
	
	public boolean insertUrlData(UrlsData urlsData);
}

package com.url.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.url.urlshortener.beans.UrlRequestBean;
import com.url.urlshortener.service.UrlShortenerService;

@RestController
public class UrlShortenerController {
	
	@Autowired
	private UrlShortenerService urlShortenerService;

	@RequestMapping(value= "/urlshortener", method = RequestMethod.POST,consumes = {"application/json"}, produces = {"application/json"})
	public String shortenUrl(@RequestBody String originalUrl,HttpServletRequest httpServletRequest) {
		
		UrlRequestBean urlRequestBean = new UrlRequestBean();
		urlRequestBean.setOrginalUrl(originalUrl);
		String shortUrl = urlShortenerService.shortenUrl(urlRequestBean, httpServletRequest.getRequestURI());
		return shortUrl;
		
	}
	
}

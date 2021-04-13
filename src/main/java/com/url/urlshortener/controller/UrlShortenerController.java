package com.url.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.url.urlshortener.beans.UrlRequestBean;
import com.url.urlshortener.beans.UrlResponseBean;
import com.url.urlshortener.service.UrlShortenerService;

@RestController
public class UrlShortenerController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UrlShortenerController.class);
	
	@Autowired
	private UrlShortenerService urlShortenerService;

	@RequestMapping(value= "/urlShortener", method = RequestMethod.POST,consumes = {"application/json"}, produces = {"application/json"})
	public UrlResponseBean shortenUrl(@RequestBody UrlRequestBean urlRequestBean,HttpServletRequest request) {		
		LOGGER.info("Request received to shorten the url : "+ urlRequestBean.getOrginalUrl());
		String shortUrl = urlShortenerService.shortenUrl(urlRequestBean, request.getRequestURI(),false);
		String hostDetails = request.getScheme().trim()+"://"+request.getServerName()+":"+request.getServerPort();
		LOGGER.info("Request completed, shortened url is : "+ shortUrl);
		UrlResponseBean response = new UrlResponseBean(hostDetails+shortUrl);
		return response;
		
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public RedirectView redirectUrl( @PathVariable String id,HttpServletRequest request,HttpServletResponse response) {
		LOGGER.info("Redirection request received for short url : "+id);
		RedirectView redirectView = new RedirectView();
		String longUrl = urlShortenerService.getLongUrlFromIdHash(id,false);
		if(!longUrl.contains("http"))
		redirectView.setUrl("http://"+longUrl);
		else
		redirectView.setUrl(longUrl);
		LOGGER.info("Redirection request completed, complete url is : "+longUrl);
		return redirectView;
		
	}
	
	@RequestMapping(value= "/urlShortenerRedis", method = RequestMethod.POST,consumes = {"application/json"}, produces = {"application/json"})
	public UrlResponseBean shortenUrlByRedis(@RequestBody UrlRequestBean urlRequestBean,HttpServletRequest request) {		
		LOGGER.info("Request received to shorten the url : "+ urlRequestBean.getOrginalUrl());
		String shortUrl = urlShortenerService.shortenUrl(urlRequestBean, request.getRequestURI(),true);
		String hostDetails = request.getScheme().trim()+"://"+request.getServerName()+":"+request.getServerPort();
		LOGGER.info("Request completed, shortened url is : "+ shortUrl);
		UrlResponseBean response = new UrlResponseBean(hostDetails+shortUrl);
		return response;
		
	}
	
	@RequestMapping(value = "/redis/{id}", method=RequestMethod.GET)
	public RedirectView redirectUrlFromRedis( @PathVariable String id,HttpServletRequest request,HttpServletResponse response) {
		LOGGER.info("Redirection request received for short url : "+id);
		RedirectView redirectView = new RedirectView();
		String longUrl = urlShortenerService.getLongUrlFromIdHash(id,true);
		if(!longUrl.contains("http"))
		redirectView.setUrl("http://"+longUrl);
		else
		redirectView.setUrl(longUrl);
		LOGGER.info("Redirection request completed, complete url is : "+longUrl);
		return redirectView;
		
	}
	
}

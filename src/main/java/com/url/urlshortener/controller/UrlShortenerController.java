package com.url.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.url.urlshortener.beans.UrlRequestBean;
import com.url.urlshortener.service.UrlShortenerService;

@RestController
public class UrlShortenerController {
	
	@Autowired
	private UrlShortenerService urlShortenerService;

	@RequestMapping(value= "/urlshortener", method = RequestMethod.POST,consumes = {"application/json"}, produces = {"application/json"})
	public String shortenUrl(@RequestBody UrlRequestBean urlRequestBean,HttpServletRequest httpServletRequest) {
		
		
		
		String shortUrl = urlShortenerService.shortenUrl(urlRequestBean, httpServletRequest.getRequestURI());
		return shortUrl;
		
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public RedirectView redirectUrl( @PathVariable String id,HttpServletRequest request,HttpServletResponse response) {
		
		RedirectView redirectView = new RedirectView();
		String longUrl = urlShortenerService.getLongUrlFromIdHash(id);
		if(!longUrl.contains("http"))
		redirectView.setUrl("http://"+longUrl);
		else
		redirectView.setUrl(longUrl);
		return redirectView;
		
	}
	
}

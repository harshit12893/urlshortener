package com.url.urlshortener.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.urlshortener.beans.UrlData;
import com.url.urlshortener.beans.UrlRequestBean;
import com.url.urlshortener.repo.UrlRepository;
import com.url.urlshortener.utils.UrlBaseConversionUtil;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {
	
	@Autowired
	UrlBaseConversionUtil conversionUtil;
	@Autowired
	UrlRepository urlRepository;
	
	@Override
	public String shortenUrl(UrlRequestBean urlRequestBean,String localUrl) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		UrlData urlData= new UrlData();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1); 
		
		Random rand = new Random();
		long randomNumber = Math.abs(rand.nextLong());
		String base62Num = conversionUtil.convertBase10ToBase62(randomNumber);
		urlData.setIdHash(base62Num);
		urlData.setOrginalUrl(urlRequestBean.getOrginalUrl());
		urlData.setCreatedDate(date);
		urlData.setExpirationDate(c.getTime());
		String baseurl = conversionUtil.formatLocalUrl(localUrl);
		String shortenedUrl = baseurl + base62Num;
		urlData.setShortenedUrl(shortenedUrl);
		urlRepository.save(urlData);
		
		return shortenedUrl;
	}
	
	public String getLongUrlFromIdHash(String idHash) {
		
		Optional<UrlData> urlDataOption = urlRepository.findById(idHash);
		UrlData urlData =null;
		if(urlDataOption.isPresent()) {
			 urlData = urlDataOption.get();
			 return urlData.getOrginalUrl();
		}
		
		return "";
		
	}
	

}

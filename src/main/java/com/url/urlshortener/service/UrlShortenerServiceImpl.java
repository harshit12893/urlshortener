package com.url.urlshortener.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.urlshortener.beans.UrlRequestBean;
import com.url.urlshortener.utils.UrlBaseConversionUtil;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {
	
	@Autowired
	UrlBaseConversionUtil conversionUtil;
	
	@Override
	public String shortenUrl(UrlRequestBean urlRequestBean) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//UrlsData  urlData= new UrlsData();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1); 
		String oldUrl = urlRequestBean.getOrginalUrl();
		
		
		Random rand = new Random();
		long randomNumber = Math.abs(rand.nextLong());
		//System.out.println("Random number " +randomNumber);
		String base62Num = conversionUtil.convertBase10ToBase62(randomNumber);
	
		
		return base62Num;
	}
	

}

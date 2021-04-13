package com.url.urlshortener.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.url.urlshortener.beans.UrlData;
import com.url.urlshortener.beans.UrlRequestBean;
import com.url.urlshortener.repo.UrlRedisRepository;
import com.url.urlshortener.repo.UrlRepository;
import com.url.urlshortener.utils.UrlBaseConversionUtil;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenerService.class);
	@Autowired
	UrlBaseConversionUtil conversionUtil;
	@Autowired
	UrlRepository urlRepository;
	@Autowired
	UrlRedisRepository urlRedisRepo;

	@Override
	public String shortenUrl(UrlRequestBean urlRequestBean,String localUrl,boolean isRedis) {
		
		LOGGER.info("Shortening url : "+urlRequestBean.getOrginalUrl());
		Date date = new Date();
		UrlData urlData= new UrlData();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1); 
		
		Random rand = new Random();
		
		if(isRedis) {
			Long incId = urlRedisRepo.incrementID();
			String base62Num = conversionUtil.convertBase10ToBase62(incId);
			String baseurl = conversionUtil.formatLocalUrl(localUrl);
			String shortenedUrl = baseurl +"urlShortenerRedis/"+ base62Num;
			urlRedisRepo.saveUrl(base62Num,shortenedUrl ,urlRequestBean.getOrginalUrl());
			LOGGER.info("Saving Url {} data to redis db ",shortenedUrl);
			return shortenedUrl;
		} else {
		long randomNumber = Math.abs(rand.nextLong());
		String base62Num = conversionUtil.convertBase10ToBase62(randomNumber);
		urlData.setIdHash(base62Num);
		urlData.setOrginalUrl(urlRequestBean.getOrginalUrl());
		urlData.setCreatedDate(date);
		urlData.setExpirationDate(c.getTime());
		String baseurl = conversionUtil.formatLocalUrl(localUrl);
		String shortenedUrl = baseurl +"urlShortener/"+ base62Num;
		urlData.setShortenedUrl(shortenedUrl);
		LOGGER.info("Saving Url {} data to db ",shortenedUrl);
		urlRepository.save(urlData);
		return shortenedUrl;
		}
		
	}
	@Override
	public String getLongUrlFromIdHash(String idHash,boolean isRedis) {
		
		if(isRedis) {
			try {
				return urlRedisRepo.getUrl(idHash);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			
		} else {
		Optional<UrlData> urlDataOption = urlRepository.findById(idHash);
		UrlData urlData =null;
		LOGGER.info("Converting shortened url {} back to original ",idHash);
		if(urlDataOption.isPresent()) {
			 urlData = urlDataOption.get();
			 return urlData.getOrginalUrl();
		}
		}
		return "";
		
	}
	

}

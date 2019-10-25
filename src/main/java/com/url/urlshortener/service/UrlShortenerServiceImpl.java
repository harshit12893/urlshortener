package com.url.urlshortener.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.url.urlshortener.beans.UrlRequestBean;
import com.url.urlshortener.dao.UrlShortenerDao;
import com.url.urlshortener.model.UrlsData;
import com.url.urlshortener.service.UrlShortenerService;

public class UrlShortenerServiceImpl implements UrlShortenerService {


@Autowired
UrlShortenerDao urlShortenerDao;

	public static final String base62Chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRS"
			+ "TUVWXYZ0123456789";
	public static final int BASE = base62Chars.length();
	public String shortenUrl(UrlRequestBean urlRequestBean) {
		// TODO Main coding logic will go here
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		UrlsData  urlData= new UrlsData();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1); 
		String oldUrl = urlRequestBean.getOrginalUrl();
		
		
		Random rand = new Random();
		long randomNumber = Math.abs(rand.nextLong());
		System.out.println("Random number " +randomNumber);
		String base62Num = convertBase10ToBase62(randomNumber);
		urlData.setIdHash(base62Num);
		urlData.setCreatedDate(date);
		urlData.setOrginalUrl(oldUrl);
		urlData.setExpirationDate(c.getTime());
		urlShortenerDao.insertUrlData(urlData);
		//urlData.setShortenedUrl(shortenedUrl);;
		return base62Num;
//		return null;
	}
	
	public static String convertBase10ToBase62(long inputNumber) {

		StringBuilder base62=new StringBuilder("");
		if(inputNumber < 0 )
			return "";
		try {
		while(inputNumber > 0) {
			int digit=(int) (inputNumber%BASE);
			base62.append(base62Chars.charAt(digit));
			inputNumber/=BASE;
		}
		} catch (Exception e) {
			System.out.println("Error in base 62 coversion "+e);
		}
		return base62.reverse().toString();
		
	}
	
	public long convertBase62ToBase10(String number) {
		
		if(number == null)
			return -1;
		int len = number.length();
		long result=0;
		for(int i=0;i<len;i++) {
			int index = base62Chars.indexOf(number.charAt(i));
			result+=index*(long)Math.pow(BASE, i);
		}
		return result;
	}
	
	public static void main(String ar[]) {
		UrlRequestBean bean= new UrlRequestBean();
		UrlShortenerServiceImpl imp= new UrlShortenerServiceImpl();
		bean.setOrginalUrl("https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java");
		String shorter = imp.shortenUrl(bean);
		long backTo = imp.convertBase62ToBase10(shorter);
		System.out.println(shorter);
		System.out.println(backTo);
	}
}

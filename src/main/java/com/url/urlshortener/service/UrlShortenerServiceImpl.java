package com.url.urlshortener.service;

import com.url.urlshortener.beans.UrlRequestBean;

public class UrlShortenerServiceImpl implements UrlShortenerService {

	public static final String base62Chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRS"
			+ "TUVWXYZ0123456789";
	public static final int BASE = base62Chars.length();
	public String shortenUrl(UrlRequestBean urlRequestBean) {
		// TODO Main coding logic will go here
		return null;
	}
	
	public String convertBase10ToBase62(long inputNumber) {

		StringBuilder base62=new StringBuilder("");
		if(inputNumber < 0 )
			return "";
		try {
		while(inputNumber > 0) {
			int digit=(int)inputNumber%BASE;
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
}

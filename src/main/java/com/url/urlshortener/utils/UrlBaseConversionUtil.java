package com.url.urlshortener.utils;

import org.springframework.stereotype.Component;

@Component
public class UrlBaseConversionUtil {
	
	public String convertBase10ToBase62(long inputNumber) {

		StringBuilder base62=new StringBuilder("");
		if(inputNumber < 0 )
			return "";
		try {
		while(inputNumber > 0) {
			int digit=(int) (inputNumber%UrlShortenerConstants.BASE);
			base62.append(UrlShortenerConstants.base62Chars.charAt(digit));
			inputNumber/=UrlShortenerConstants.BASE;
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
			int index = UrlShortenerConstants.base62Chars.indexOf(number.charAt(i));
			result+=index*(long)Math.pow(UrlShortenerConstants.BASE, i);
		}
		return result;
	}
	
	public String formatLocalUrl(String localUrl) {
		String []addressComponents = localUrl.split("/");
		StringBuilder sb= new StringBuilder();
		for(int i=0;i<addressComponents.length-1;i++)
			sb.append(addressComponents[i]);
		sb.append("/");
		return sb.toString();
		}
}

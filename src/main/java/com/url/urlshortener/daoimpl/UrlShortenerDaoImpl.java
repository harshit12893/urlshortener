package com.url.urlshortener.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.url.urlshortener.dao.UrlShortenerDao;
import com.url.urlshortener.model.UrlsData;

@Repository
public class UrlShortenerDaoImpl implements UrlShortenerDao {
	
	@Autowired
	SessionFactory sessionFactory;

	public boolean insertUrlData(UrlsData urlsData) {
		boolean result;
		Session session = sessionFactory.getCurrentSession();
		try {
		session.saveOrUpdate(urlsData);
		result=true;
	} catch (Exception e) {
		result=false;
		System.out.println("Error while saving url data "+ e);
	}
	return result;
	}
}

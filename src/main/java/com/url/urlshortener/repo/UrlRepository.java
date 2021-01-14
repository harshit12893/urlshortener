package com.url.urlshortener.repo;

import org.springframework.data.repository.CrudRepository;

import com.url.urlshortener.beans.UrlData;

public interface UrlRepository extends CrudRepository<UrlData, String>{

}

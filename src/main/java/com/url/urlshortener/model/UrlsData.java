package com.url.urlshortener.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "urls_data")
public class UrlsData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idHash;
	private String orginalUrl;
	private String shortenedUrl;
	private Date createdDate;
	private Date expirationDate;
	
	public String getIdHash() {
		return idHash;
	}
	public void setIdHash(String idHash) {
		this.idHash = idHash;
	}
	public String getOrginalUrl() {
		return orginalUrl;
	}
	public void setOrginalUrl(String orginalUrl) {
		this.orginalUrl = orginalUrl;
	}
	public String getShortenedUrl() {
		return shortenedUrl;
	}
	public void setShortenedUrl(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
		
	
}

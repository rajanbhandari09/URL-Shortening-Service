package com.urlshorteningservice.bo;

public interface UrlShorteningService {

	public String shortenUrl(String url);

	public String getRedirectUrl(String url);
	
}

package com.urlshorteningservice.bo;

public class Url {
	private static int HITCOUNT_THRESHOLD = 10;
	private String url;
	private int hitCount;
	
	public Url(String url, int hitCount) {
		this.url = url;
		this.hitCount = hitCount;
	}

	public String getUrl() {
		return url;
	}
	
	public int getHitCount(){
		return hitCount;
	}

	public boolean isThresholdReached() {
		return hitCount == HITCOUNT_THRESHOLD;
	}
	
	public void incrementHitCount(){
		++hitCount;
	}
	
}

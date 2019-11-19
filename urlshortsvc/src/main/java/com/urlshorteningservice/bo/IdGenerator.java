package com.urlshorteningservice.bo;

public interface IdGenerator {

	public String calculateShortId(Integer id);
	public Integer calculateUniqueId(String shortId);
	
}

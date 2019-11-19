package com.urlshorteningservice.bo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

public class UrlShorteningServiceImplTest {
	private UrlShorteningServiceImpl urlShorteningServiceImpl;
	
	@Test
	public void testUrlShorteningIfNotExists() {
		String url = "https://www.google.com/page1/profile1";
		FileHandler fileHandler = EasyMock.createNiceMock(FileHandler.class);
		EasyMock.expect(fileHandler.getLastId()).andReturn(10000);
		fileHandler.add(10001, new Url(url,0));
		EasyMock.expectLastCall();
		EasyMock.expect(fileHandler.initialize()).andReturn(new ArrayList<>());
		EasyMock.replay(fileHandler);
		IdGenerator idGenerator = EasyMock.createNiceMock(IdGenerator.class);
		EasyMock.expect(idGenerator.calculateShortId(10001)).andReturn("cLs");
		EasyMock.replay(idGenerator);
		urlShorteningServiceImpl = new UrlShorteningServiceImpl(fileHandler,idGenerator);
		String  actualShortenedUrl = urlShorteningServiceImpl.shortenUrl(url);
		String expectedShortenedUrl = "http://localhost:8090/urlshortsvc/rest/cLs";
		assertEquals(expectedShortenedUrl,actualShortenedUrl);
	}
	
	@Test
	public void testUrlShorteningIfExists() {
		String url = "https://www.google.com/page1/profile1";
		FileHandler fileHandler = EasyMock.createNiceMock(FileHandler.class);
		EasyMock.expect(fileHandler.initialize()).andReturn(new ArrayList<>());
		EasyMock.expect(fileHandler.getLastId()).andReturn(10000);
		fileHandler.add(10001, new Url(url,0));
		EasyMock.expectLastCall();
		EasyMock.replay(fileHandler);
		IdGenerator idGenerator = EasyMock.createNiceMock(IdGenerator.class);
		EasyMock.expect(idGenerator.calculateShortId(10001)).andReturn("cLs");
		EasyMock.expect(idGenerator.calculateShortId(10001)).andReturn("cLs");
		EasyMock.replay(idGenerator);
		urlShorteningServiceImpl = new UrlShorteningServiceImpl(fileHandler,idGenerator);
		String  shortenedUrl1 = urlShorteningServiceImpl.shortenUrl(url);
		String  shortenedUrl2 = urlShorteningServiceImpl.shortenUrl(url);
		assertEquals(shortenedUrl1,shortenedUrl2);
	}
	
	@Test
	public void testGetRedirectUrl(){
		String url = "https://www.google.com/page1/profile1";
		FileHandler fileHandler = EasyMock.createNiceMock(FileHandler.class);
		EasyMock.expect(fileHandler.initialize()).andReturn(new ArrayList<>());
		EasyMock.expect(fileHandler.getLastId()).andReturn(10000);
		fileHandler.add(10001, new Url(url,0));
		EasyMock.expectLastCall();
		EasyMock.replay(fileHandler);
		IdGenerator idGenerator = EasyMock.createNiceMock(IdGenerator.class);
		EasyMock.expect(idGenerator.calculateShortId(10001)).andReturn("cLs");
		EasyMock.expect(idGenerator.calculateShortId(10001)).andReturn("cLs");
		EasyMock.expect(idGenerator.calculateUniqueId("cLs")).andReturn(10001);
		EasyMock.replay(idGenerator);
		urlShorteningServiceImpl = new UrlShorteningServiceImpl(fileHandler,idGenerator);
		String  shortenedUrl1 = urlShorteningServiceImpl.shortenUrl(url);
		String shortUrl = "http://localhost:8090/urlshortsvc/rest/cLs";
		String actualRedirectUrl = urlShorteningServiceImpl.getRedirectUrl(shortUrl);
		assertEquals(url,actualRedirectUrl);
	}
	

}

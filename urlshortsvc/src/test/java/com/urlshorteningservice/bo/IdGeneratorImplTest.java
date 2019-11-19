package com.urlshorteningservice.bo;

import static org.junit.Assert.*;

import org.junit.Test;

public class IdGeneratorImplTest {
	IdGenerator idGenerator;
	
	@Test
	public void testShortIdCalculation() {
		idGenerator = new IdGeneratorImpl();
		String expectedShortId = "cLs";
		String actualShortId = idGenerator.calculateShortId(10000);
		assertEquals(expectedShortId,actualShortId);
	}
	
	@Test
	public void testUniqueIdCalculation(){
		idGenerator = new IdGeneratorImpl();
		Integer expectedUniqueId = 10000;
		Integer actualUniqueId = idGenerator.calculateUniqueId("cLs");
		assertEquals(expectedUniqueId,actualUniqueId);
	}
}

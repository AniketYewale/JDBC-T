package com.indus.training.persist.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.dao.impl.CountryDAO;
import com.indus.training.persist.entity.Country;
import com.indus.training.persist.exception.PersistException;


public class TestCountryDAO 
{
	private ICountryDAO iCountryDAOObj;
	
	@Before
	public void setUp() throws Exception 
	{
		iCountryDAOObj = new CountryDAO();
	}

	@After
	public void tearDown() throws Exception 
	{
		iCountryDAOObj = null;
	}

	@Test
	public void searchByCountryIdTest() 
	{
		String countryId = "IN";
		Country countryExpObj = new Country();
		countryExpObj.setCountryId("IN");
		countryExpObj.setCountryName("India");
		countryExpObj.setRegionId(3);
		
		Country countryActualObj = null;
		try {
			countryActualObj = iCountryDAOObj.searchByCountryId(countryId);
			System.out.println(countryActualObj);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(countryExpObj, countryActualObj);
	}
	
	@Test
	public void searchByCountryNameTest()
	{
		String countryName = "Australia";
		
		Country countryExpObj = new Country();
		countryExpObj.setCountryId("AU");
		countryExpObj.setCountryName("Australia");
		countryExpObj.setRegionId(3);
		
		Country countryActObj = null;
		
		try {
			countryActObj = iCountryDAOObj.searchByCountryName(countryName);
			
			System.out.println(countryActObj);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(countryExpObj, countryActObj);
		
	}

	
/*
	@Test
	public void insertintoTableTest()
	{
		Country countryObj = new Country();
		
		countryObj.setCountryId("SA");
		countryObj.setCountryName("SouthAfrica");
		countryObj.setRegionId(2);
		
		Boolean expIsInserted = true;
		Boolean actIsInserted = null;
		try {
			  actIsInserted = iCountryDAOObj.insert(countryObj);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expIsInserted,actIsInserted);	
	}
*/
	
/*
	@Test
	public void deletebyCountryIdTest()
	{
		String countryId = "SA";
		
		Boolean isExpDeleted = true;
		Boolean isActDeleted = null;
		
		try {
			isActDeleted = iCountryDAOObj.deleteByCountryId(countryId);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(isExpDeleted, isActDeleted);
	}
*/
	
}

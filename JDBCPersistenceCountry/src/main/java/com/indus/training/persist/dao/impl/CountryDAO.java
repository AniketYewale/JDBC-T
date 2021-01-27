package com.indus.training.persist.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import com.indus.training.persist.dao.ICountryDAO;
import com.indus.training.persist.entity.Country;
import com.indus.training.persist.exception.PersistException;
import com.indus.training.persist.util.DBConnectionFactory;

public class CountryDAO implements ICountryDAO
{

	DBConnectionFactory dbConnFactoryObj = DBConnectionFactory.getFactoryObject();
	
/*	
	private Connection getDBConnection() throws PersistException    //We wrote this method in 'ConnectionUtil' class. And changed it to public inorder to be accessed by methods here.
	{
		Connection dbConObj = null;
		
		try {
			dbConObj = DriverManager.getConnection("jdbc:postgresql://localhost:5432/IndusLearn", "postgres", "mission29");
		} catch (SQLException e) {
			throw new PersistException("HR Schema: Countries Table :Country Class : searchByCountryId : Processing Select query and ResultSet", e);
		}
		
		return dbConObj;
	}
*/	
	
	public Country searchByCountryId(String countryId) throws PersistException 
	{
		
		Country countryObj = null;
		
		/**
		 * Step 1: Pro Active Coding : Check input arguments are valid
		 */
		
		if(countryId == null || countryId.isEmpty())
		{
			throw new PersistException("HR Schema : Countries Table : Country Class: searchByCountryId : Input argument is null");
		}
		
		
		/**
		 * Step 2: Set up InfraStructure: Connect to Driver Manager.
		 * Provide the qualities we are looking in a driver to get my job done. 
		 * To do that we need to provide 4 properties to Driver Manager and ask for Connection. 
		 */
		
		
		Connection hrDbConObj = null;
		Statement hrDbCountryStmt = null;
		ResultSet hrDbCountryResultset = null;

		try
		{
			/**
			 * Step 3: Get Connection
			 */
			
			hrDbConObj = dbConnFactoryObj.getDBConnection();
		   
			/**
			 * Step 4: Get Statement
			 */
			
		    hrDbCountryStmt = hrDbConObj.createStatement();
		    
		    /**
			 * Step 5: Define & Execute SQL statement
			 */
		    
		    String selectSQL = "SELECT * FROM HR.COUNTRIES C WHERE C.COUNTRY_ID = '" +countryId+ "'";
		    
		    hrDbCountryResultset = hrDbCountryStmt.executeQuery(selectSQL);
		    
		    /**
			 * Step 6: Process Result
			 */
		    
		    while(hrDbCountryResultset.next()) {
		    	countryObj = new Country();
		    	countryObj.setCountryId(hrDbCountryResultset.getString("COUNTRY_ID"));
		    	countryObj.setCountryName(hrDbCountryResultset.getString("COUNTRY_NAME"));
		    	countryObj.setRegionId(hrDbCountryResultset.getInt("REGION_ID"));
		    }
		    
		}catch(Exception ex){
			throw new PersistException("HR Schema: Countries Table : Country Class : searchByCountryId : Processing Select query and ResultSet", ex);
		}
		
		/**
		 * Step 7: After we complete the work with connection, we need to code finally block to close the
		 resources we open. 
		 */
	
		finally {
			if(hrDbCountryResultset != null) {
				try {
					hrDbCountryResultset.close();
					}catch(SQLException e){
						throw new PersistException(
								"HR Schema: Countries Table :Country Class : searchByCountryId : Unable to close Result Set ",
								e);
					}
			}
			
			if(hrDbCountryStmt != null) {
				try {
					hrDbCountryStmt.close();
					}catch(SQLException e){
						throw new PersistException(
								"HR Schema: Countries Table :Country Class : searchByCountryId : Unable to close JDBC Statement ",
								e);
					}
			}
			
			if(hrDbConObj != null) {
				dbConnFactoryObj.closeConnection(hrDbConObj);
			}	
		}
	   
		return countryObj;
	}

	public Country searchByCountryName(String countryName) throws PersistException 
	{
		Country countryObj = null;
		
		if(countryName == null || countryName.isEmpty())
		{
			throw new PersistException("HRSchema : Countries Table : Country Class : searchByCountryName : Input argument is null");
		}
		
		Connection hrDbConObj = null;
		Statement hrDbStmntObj = null;
		ResultSet hrDbResultset = null;
		
		try
		{
			hrDbConObj = dbConnFactoryObj.getDBConnection();
		
			hrDbStmntObj = hrDbConObj.createStatement();
		
			String selectSQL = "SELECT * FROM HR.COUNTRIES C WHERE C.COUNTRY_NAME = '" +countryName+ "'";
		
			hrDbResultset = hrDbStmntObj.executeQuery(selectSQL);
			
			while(hrDbResultset.next())
			{
				countryObj = new Country();
				countryObj.setCountryId(hrDbResultset.getString("COUNTRY_ID"));
				countryObj.setCountryName(hrDbResultset.getString("COUNTRY_NAME"));
				countryObj.setRegionId(hrDbResultset.getInt("REGION_ID"));
			}
		}
		catch(Exception e){
			throw new PersistException("HRSchema : Countries Table : Country Class : searchByCountryName : Processing Select query & ResultSet",e);
		}
		finally {
			if(hrDbResultset != null)
			{
				try {
					hrDbResultset.close();
				} catch (SQLException e) {
					throw new PersistException("HRSchema : Countries Table : Country Class : searchByCountryName : Unable to close ResultSet",e);
				}
			}
			
			if(hrDbStmntObj != null)
			{
				try {
					hrDbStmntObj.close();
				} catch (SQLException e) {
					throw new PersistException("HRSchema : Countries Table : Country Class : searchByCountryName : Unable to close JDBC Statement",e);
				}
			}
			
			if(hrDbConObj != null)
			{
				dbConnFactoryObj.closeConnection(hrDbConObj);
			}
		}
		
		return countryObj;
	}

	public Boolean insert(Country countryObj) throws PersistException 
	{
		 Boolean isInserted = null;
		 
		 Connection hrDbConnObj = null;
		 Statement hrDbStmntObj = null;
		 
		 String countryId = countryObj.getCountryId();
		 String countryName = countryObj.getCountryName();
		 int regionId = countryObj.getRegionId();
		 
		 try
		 {
			 hrDbConnObj = dbConnFactoryObj.getDBConnection();
			 hrDbStmntObj = hrDbConnObj.createStatement();

			 String selectSQL = "INSERT INTO HR.COUNTRIES VALUES ('"+countryId+"', '"+countryName+"', "+regionId+")";
			 hrDbStmntObj.executeQuery(selectSQL);
			
			 
			/*
			 * Country searchObj = searchByCountryId(countryId); //Search in DB if record is
			 * inserted by searching and comparing if each field exists in database
			 * 
			 * if(searchObj.getCountryId() == countryId && searchObj.getCountryName() ==
			 * countryName && searchObj.getRegionId() == regionId) { isInserted = true; }
			 * else { isInserted = false; }
			 */
			 
		 }catch(Exception e){
			 throw new PersistException("HRSchema : Countries Table : Country Class : insert : Processing Select query",e);
		 }finally {
			 if(hrDbStmntObj != null)
			 {
				 try {
					hrDbStmntObj.close();
				} catch (SQLException e) {
					throw new PersistException("HRSchema : Countries Table : Country Class : insert : Unable to close JDBC Statement",e);
				}
			 }
			 
			 if(hrDbConnObj != null)
			 {
				 dbConnFactoryObj.closeConnection(hrDbConnObj);
			 }
		 }
 
		 isInserted = true; 
		return isInserted;
	}

	
	public Boolean update(String countryId) throws PersistException {
		return null;
	}

	
	public Boolean deleteByCountryId(String countryId) throws PersistException 
	{
		Boolean isDeleted = null;
		
		Connection hrDbConn = null;
		Statement hrDbStmnt = null;
		
		try
		{
			hrDbConn = dbConnFactoryObj.getDBConnection();
			hrDbStmnt = hrDbConn.createStatement();
			
			String selectSQL = "DELETE FROM HR.COUNTRIES WHERE COUNTRY_ID = '"+countryId+"'";
			hrDbStmnt.executeQuery(selectSQL);

		}catch(Exception e) {
			throw new PersistException("HRSchema : Countries Table : Country Class : deleteByCountryIddeleteByCountryId : Processing Select query & ResultSet");
		}finally {
			if(hrDbStmnt != null)
			{
				try {
					hrDbStmnt.close();
				} catch (SQLException e) {
					throw new PersistException("HRSchema : Countries Table : Country Class : deleteByCountryIddeleteByCountryId : Unable to close JDBC Statement");
				}
			}
			
			if(hrDbConn != null)
			{
				dbConnFactoryObj.closeConnection(hrDbConn);
			}
		}
		
		isDeleted = true;
		return isDeleted;
	}

	
	
	public Set<Country> searchByRegionId(int regionId) throws PersistException 
	{
		
		
		return null;
	}

	
}

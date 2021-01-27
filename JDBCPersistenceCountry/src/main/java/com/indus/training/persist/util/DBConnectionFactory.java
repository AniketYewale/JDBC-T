package com.indus.training.persist.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.indus.training.persist.exception.PersistException;


// Singleton Design Pattern
//https://www.geeksforgeeks.org/singleton-design-pattern/

public class DBConnectionFactory 
{
	
	
	/*  This is right. But we will put URL, username and password in a file 'DBProperties.txt'
	 *  We do so because if we want to change any configuration, we just do that in DBProperties file.
	 
	public Connection getDBConnection() throws PersistException // We Changed this method to public from private when we wrote this in a separate new Class 'ConnectionUtil'; inorder to accessed by methods of other classes
	{ 
		Connection dbConObj = null;

		try {
				dbConObj = DriverManager.getConnection("jdbc:postgresql://localhost:5432/IndusLearn", "postgres", "mission29");
			} catch (SQLException e) {
			throw new PersistException("HR Schema: Countries Table : Country Class : Problem creating connection object", e);
		}

		return dbConObj;
	}
	
	*/	

	
	private static DBConnectionFactory connFactObj = null;
	
	private Properties dbProperties = new Properties();
	
	

	private DBConnectionFactory(String fileName)  // Private Constructor cannot call 'new'. So creates no object.  
	{

		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(fileName));
			dbProperties.load(inputStream);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	} 
	
	
	// Factory method 
	public static DBConnectionFactory getFactoryObject()
	{
		if(connFactObj == null)
		{
			connFactObj = new DBConnectionFactory("C:\\IndusTraining\\Advanced Java\\4. JDBC Basics\\JDBCPersistenceCountry\\src\\main\\resources\\DBProperties.txt");
		}
		return connFactObj;
		
	} 
	
	
	public Connection getDBConnection() throws PersistException 
	{ 
		Connection dbConObj = null;

		try {
				dbConObj = DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties.getProperty("username"), dbProperties.getProperty("password"));
			} catch (SQLException e) {
			throw new PersistException("HR Schema: Countries Table : Country Class : Problem creating connection object", e);
		}

		return dbConObj;
	}
	
	
	public void closeConnection(Connection conObj) throws PersistException
	{
		try {
			conObj.close();
		} catch (SQLException e) {
			throw new PersistException("DBConnectionFactory : getDBConnection method : Problem closing connection object");
		}
	}
	
	
}

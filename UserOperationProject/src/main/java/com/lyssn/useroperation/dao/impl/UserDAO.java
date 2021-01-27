package com.lyssn.useroperation.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

import com.lyssn.useroperation.dao.IUserDAO;
import com.lyssn.useroperation.entity.User;
import com.lyssn.useroperation.exception.UserPersistException;


public class UserDAO implements IUserDAO
{
	User userObj = null;
	Connection hrDbConObj = null;
	Statement hrDbCountryStmt = null;
	ResultSet hrDbCountryResultset = null;
	Scanner sc = new Scanner(System.in);

	private Connection getDBConnection() throws UserPersistException
	{
		Connection dbConObj = null;

		try {
			dbConObj = DriverManager.getConnection("jdbc:postgresql://localhost:5432/IndusLearn", "postgres", "mission29");
		} catch (SQLException e) {
			throw new UserPersistException("HR Schema: User Table :User Class : Processing Select query and ResultSet", e);
		}

		return dbConObj;
	}


	public void displayAllUsers() throws UserPersistException 
	{
		try
		{
			hrDbConObj = getDBConnection();

			hrDbCountryStmt = hrDbConObj.createStatement();

			String selectSQL = "SELECT * FROM hr.users";

			hrDbCountryResultset = hrDbCountryStmt.executeQuery(selectSQL);


			while(hrDbCountryResultset.next()) {
				userObj = new User();
				userObj.setUserid(hrDbCountryResultset.getLong("USERID"));
				userObj.setFname(hrDbCountryResultset.getString("FNAME"));
				userObj.setLname(hrDbCountryResultset.getString("LNAME"));
				userObj.setSignupdate(hrDbCountryResultset.getTimestamp("SIGNUPDATE"));
				System.out.println(userObj);
			}

		}catch(Exception ex){
			throw new UserPersistException("HR Schema: User Table : User Class : displayAllUsers : Processing Select query and ResultSet", ex);
		}


		finally {
			if(hrDbCountryResultset != null) {
				try {
					hrDbCountryResultset.close();
				}catch(SQLException e){
					throw new UserPersistException(
							"HR Schema: User Table : User Class : displayAllUsers : Unable to close Result Set ",
							e);
				}
			}

			if(hrDbCountryStmt != null) {
				try {
					hrDbCountryStmt.close();
				}catch(SQLException e){
					throw new UserPersistException(
							"HR Schema: User Table : User Class : displayAllUsers : Unable to close JDBC Statement ",
							e);
				}
			}

			if(hrDbConObj != null) {
				try {
					hrDbConObj.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
	}


	public User findUserByID(Long userid) throws UserPersistException 
	{	
		userObj = null;
		
		if(userid == null)
		{
			throw new UserPersistException("HR Schema : User Table : User Class: findUserByID : Input argument is null");
		}

		try
		{
			hrDbConObj = getDBConnection();

			hrDbCountryStmt = hrDbConObj.createStatement();

			String selectSQL = "SELECT * FROM hr.users u WHERE u.userid = "+userid;

			hrDbCountryResultset = hrDbCountryStmt.executeQuery(selectSQL);

			while(hrDbCountryResultset.next()) {
				userObj = new User();
				userObj.setUserid(hrDbCountryResultset.getLong("USERID"));
				userObj.setFname(hrDbCountryResultset.getString("FNAME"));
				userObj.setLname(hrDbCountryResultset.getString("LNAME"));
				userObj.setSignupdate(hrDbCountryResultset.getTimestamp("SIGNUPDATE"));
			}

		}catch(Exception ex){
			throw new UserPersistException("HR Schema: User Table : User Class : findUserByID : Processing Select query and ResultSet", ex);
		}


		finally {
			if(hrDbCountryResultset != null) {
				try {
					hrDbCountryResultset.close();
				}catch(SQLException e){
					throw new UserPersistException(
							"HR Schema: User Table : User Class : findUserByID : Unable to close Result Set ",
							e);
				}
			}

			if(hrDbCountryStmt != null) {
				try {
					hrDbCountryStmt.close();
				}catch(SQLException e){
					throw new UserPersistException(
							"HR Schema: User Table : User Class : findUserByID : Unable to close JDBC Statement ",
							e);
				}
			}

			if(hrDbConObj != null) {
				try {
					hrDbConObj.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}

		return userObj;
	}



	public void createUser() throws UserPersistException  
	{	
		try
		{
			hrDbConObj = getDBConnection();

			hrDbCountryStmt = hrDbConObj.createStatement();

			System.out.println("\nEnter UserID: ");
			Long newUserid = sc.nextLong();
			System.out.println("\nEnter first name: ");
			String newfname = sc.next();
			System.out.println("\nEnter last name: ");
			String newlname = sc.next();
			Timestamp newSignupdate = new Timestamp(System.currentTimeMillis());

			String selectSQL = "INSERT INTO hr.users (userid,fname,lname,signupdate) VALUES ("+newUserid+",'"+newfname+"','"+newlname+"','"+newSignupdate+"')";
			int result = hrDbCountryStmt.executeUpdate(selectSQL);

			System.out.println("\n"+result+" Record created");

		}catch(Exception ex){
			System.out.println("\nUser record not created\n");
			throw new UserPersistException(ex);
		}

		finally {
			if(hrDbCountryStmt != null) {
				try {
					hrDbCountryStmt.close();
				}catch(SQLException e){
					throw new UserPersistException(
							"HR Schema: User Table : User Class : createUser : Unable to close JDBC Statement ",e);
				}
			}

			if(hrDbConObj != null) {
				try {
					hrDbConObj.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
	}

	public void updateUserByID(Long userid) throws UserPersistException 
	{	
		try
		{
			hrDbConObj = getDBConnection();

			hrDbCountryStmt = hrDbConObj.createStatement();

			System.out.println("\nEnter first name to update: ");
			String updatedfname = sc.next();
			System.out.println("\nEnter last name to update: ");
			String updatedlname = sc.next();
			Timestamp newSignupdate = new Timestamp(System.currentTimeMillis());

			String selectSQL = "Update hr.users Set fname ='"+updatedfname+"', lname ='"+updatedlname+"', signupdate = '"+newSignupdate+"' where userid="+userid;
			int result = hrDbCountryStmt.executeUpdate(selectSQL);

			System.out.println("\n"+result+" Record updated");

		}catch(Exception ex){
			throw new UserPersistException("UserID doesn't exist",ex);
		}


		finally {

			if(hrDbCountryStmt != null) {
				try {
					hrDbCountryStmt.close();
				}catch(SQLException e){
					throw new UserPersistException(
							"HR Schema: User Table : User Class : updateUserByID : Unable to close JDBC Statement ",e);
				}
			}

			if(hrDbConObj != null) {
				try {
					hrDbConObj.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}


	public void deleteUserByID(Long userid) throws UserPersistException 
	{	
		Connection hrDbConObj = null;
		Statement hrDbCountryStmt = null;

		try
		{
			hrDbConObj = getDBConnection();

			hrDbCountryStmt = hrDbConObj.createStatement();

			String selectSQL = "DELETE from hr.users where userid ="+userid;
			int result = hrDbCountryStmt.executeUpdate(selectSQL);

			System.out.println("\n"+result+" Record deleted");

		}catch(Exception ex){
			throw new UserPersistException("UserID doesn't exist",ex);
		}


		finally {

			if(hrDbCountryStmt != null) {
				try {
					hrDbCountryStmt.close();
				}catch(SQLException e){
					throw new UserPersistException(
							"HR Schema: User Table : User Class : deleteUserByID : Unable to close JDBC Statement ",e);
				}
			}

			if(hrDbConObj != null) {
				try {
					hrDbConObj.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
	}


}

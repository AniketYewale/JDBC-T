package com.lyssn.useroperation.dao;

import com.lyssn.useroperation.entity.User;
import com.lyssn.useroperation.exception.UserPersistException;

public interface IUserDAO 
{
	public User findUserByID(Long userid) throws UserPersistException;
	public void displayAllUsers() throws UserPersistException;
	public void createUser() throws UserPersistException;
	public void updateUserByID(Long userid) throws UserPersistException;
	public void deleteUserByID(Long userid) throws UserPersistException;
}

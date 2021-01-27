package com.lyssn.useroperation.main;

import java.util.Scanner;

import com.lyssn.useroperation.dao.IUserDAO;
import com.lyssn.useroperation.dao.impl.UserDAO;
import com.lyssn.useroperation.entity.User;
import com.lyssn.useroperation.exception.UserPersistException;

public class UserMain 
{
	public static void main(String[] args) 
	{
		IUserDAO iUserDAOObj = new UserDAO();
		User userObj = new User();
		
	    Scanner sc = new Scanner(System.in);
		char ch;
			
		 try {
			do {
				 System.out.println("\nWhat would you like to do? ");
				 System.out.println("1. Find User by UserID");
				 System.out.println("2. Display all user records");
				 System.out.println("3. Insert new record:");
				 System.out.println("4. Update a record");
				 System.out.println("5. Delete a record");
				 System.out.println("\nEnter your choice: ");
				 int choice = sc.nextInt();

				 switch (choice) 
				 {
					 case 1:
							  System.out.println("\nEnter the UserID you want to find: ");
							  Long userid = sc.nextLong();
						 	  userObj  = iUserDAOObj.findUserByID(userid);
						 	  if(userObj!=null)
						 	  {
						 		  System.out.println("User Found : " + userObj);
						 	  }
						 	  else
						 	  {
							    System.out.println("UserID doesn't exist");
							  }
						 	 break;
				 
					 case 2:
							 	 System.out.println("\nDisplaying all Users: \n");
								 iUserDAOObj.displayAllUsers();
								 break;
							 
					 case 3:
							 	 iUserDAOObj.createUser();
							 	 break;
	
					 case 4:
							  System.out.println("\nEnter the User ID of user you want to update: ");
							  Long updateUserid = sc.nextLong();
							  userObj = iUserDAOObj.findUserByID(updateUserid);
							  if(userObj!=null)
						 	  {
								 iUserDAOObj.updateUserByID(updateUserid);
						 	  }
						 	  else
						 	  {
							    System.out.println("UserID doesn't exist");
							  }
						 	  
						 	  break;
						 	  
					 case 5:
							  System.out.println("\nEnter the User ID of user you want to delete: ");
							  Long deleteUserid = sc.nextLong();
							  userObj = iUserDAOObj.findUserByID(deleteUserid);
							  if(userObj!=null)
						 	  {
								 iUserDAOObj.deleteUserByID(deleteUserid);
						 	  }
						 	  else
						 	  {
							    System.out.println("UserID doesn't exist");
							  }
						 	  
						 	  break;
							
				     default:
						 	System.out.println("\nWrong Choice \n ");
						 	break;
			    }

				System.out.println("\nDo you want to continue (Type Y or N) : ");
				ch = sc.next().charAt(0);
			 } while (ch == 'Y' || ch == 'y');
		} catch (UserPersistException e) {
			e.printStackTrace();
		}
		System.exit(0);
		sc.close(); 
	}

}

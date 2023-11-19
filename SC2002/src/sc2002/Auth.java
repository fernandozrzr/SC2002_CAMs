package sc2002;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

import java.util.Enumeration;
import java.util.HashMap;

public class Auth {

	// initialize the dictionary passwordDict
	static Hashtable<String, String> studentPasswordDict = new Hashtable<>();
	static Hashtable<String, String> staffPasswordDict = new Hashtable<>();

	public static void login( String userID, String password, int domain) {
		
		System.out.println("in Auth.login");

		//check if respective passwordDict is load before 
		switch(domain) {
		case 1: //student
			if(studentPasswordDict.isEmpty()==true) {
				try {
					studentPasswordDict = getPasswordDict(domain);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// valid user
			if ( studentPasswordDict.containsKey(userID)) {
				if (studentPasswordDict.get(userID).equals(password)) {
					System.out.print("Login succesful!");
					try {
						camsApp.currentUser = FileManager.createUserObject(userID, FileManager.getFilePath(domain),domain);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// if user still using default password
					if(studentPasswordDict.get(userID).equals("Password")) {
						System.out.println("Please change your password!!!");
						ChangePassword(camsApp.currentUser);
					}				
				}
				
				// user found but wrong password
				else {
					System.out.println("Wrong passsword!");
				}
			}
			
			// default case
			else {
				System.out.println("user not found!");
			}
			break;
			
		case 2://staff
			if(staffPasswordDict.isEmpty()==true) {
				try {
					staffPasswordDict = getPasswordDict(domain);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//passwordDict =  staffPasswordDict;
			// valid user
			if ( staffPasswordDict.containsKey(userID)) {
				if (staffPasswordDict.get(userID).equals(password)) {
					System.out.print("Login succesful!");
					try {
						camsApp.currentUser = FileManager.createUserObject(userID, FileManager.getFilePath(domain),domain);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// if user still using default password
					if(staffPasswordDict.get(userID).equals("Password")) {
						System.out.println("Please change your password!!!");
						ChangePassword(camsApp.currentUser);
					}	
				}
				
				// user found but wrong password
				else {
					System.out.println("Wrong passsword!");
				}
			}
			
			// default case
			else {
				System.out.println("user not found!");
			}
			break;
		}
		
		System.out.println(studentPasswordDict);
		System.out.println(staffPasswordDict);
	}
	
	
	public static void ChangePassword(User currentUser) {
		 
		Scanner sc= new Scanner(System.in);
		String user;
		
		//display currentUser details
		System.out.println("Change Password");
		System.out.println("UserID: "+currentUser.getUserID());
		
		//get user new password
		System.out.println("Enter new Password:\n");
		String newpass =sc.next();
		
		//check the user domain 
		String type = currentUser.getClass().toString();
		type=type.substring(type.lastIndexOf("S"));
		
		//update respective passworDict
		switch(type) {
		case "Student":
			System.out.println("Change password for Student...");
			user = currentUser.getUserID();
			studentPasswordDict.put(user,newpass);
			System.out.println(studentPasswordDict);
			System.out.println("done");
			break;
			
		case "Staff" :
			System.out.println("Change password for Staff...");
			user =currentUser.getUserID();
			staffPasswordDict.put(user, newpass);
			System.out.println(staffPasswordDict);
			System.out.println("done");
			break;
		}
		System.out.println("Password changed successfully...");	
	}
	
	public static Hashtable<String,String> getPasswordDict(int domain) throws Exception {
		return FileManager.readFile(FileManager.getFilePath(domain));
	}

}

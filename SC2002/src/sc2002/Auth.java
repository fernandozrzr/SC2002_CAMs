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

	// call file manager class to fill up the dictionary
	/*
	 * public Auth(String userID, String password) { this.userID = userID;
	 * this.password = password; }
	 */

	public static User login( String userID, String password, int domain) {
		
		System.out.println("in Auth.login");

		//initialize passwordDict
		Hashtable<String, String> passwordDict = null;
		
		//check if respective passwordDict is load before 
		switch(domain) {
		case 1: 
			if(studentPasswordDict.isEmpty()==true) {
				try {
					studentPasswordDict = getPasswordDict(domain);
					passwordDict = studentPasswordDict;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				passwordDict = studentPasswordDict;
			}
			break;
		case 2:
			if(staffPasswordDict.isEmpty()==true) {
				try {
					staffPasswordDict = getPasswordDict(domain);
					passwordDict =  staffPasswordDict;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				passwordDict = staffPasswordDict;
			}
			
			break;
		}
		
		System.out.println(passwordDict);
		// initialize currentUser to default
		User currentUser = null;

		// valid user
		// System.out.println(passwordDict.containsKey(userID));
		if ( passwordDict.containsKey(userID)) {

			System.out.println("This user!");
			// System.out.println( passwordDict.get(userID));

			if (passwordDict.get(userID).equals(password)) {

				System.out.print("Login succesful!");
				try {
					currentUser = FileManager.createUserObject(userID, FileManager.getFilePath(domain),domain);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// user found but wrong password
			else {
				System.out.println("Wrong passsword lol!");
			}
		}
		// default case
		else {
			System.out.println("user not found!");
		}
		//passwordDict.clear();
		// System.out.println("exit auth.login()...");
		return currentUser;
	}

	public static void ChangePassword(User currentUser) {
		
		//get user input 
		Scanner sc= new Scanner(System.in);
		System.out.println("Change Password");
		System.out.println("UserID: "+currentUser.getUserID());
		System.out.println("Enter new Password:\n");
		String newpass =sc.next();
		
		
		String type = currentUser.getClass().toString();
		type=type.substring(type.lastIndexOf("S"));
		System.out.println(type);
		switch(type) {
		case "Student":
			System.out.println("Changing password for Student...");
			String user = currentUser.getUserID();
			studentPasswordDict.replace(user,newpass);
			System.out.println(studentPasswordDict);
			System.out.println("done");
			break;
			
		case "Staff" :
			System.out.println("Changing password for Staff...");
			user =currentUser.getUserID();
			staffPasswordDict.put(user, newpass);
			System.out.println(staffPasswordDict);
			System.out.println("done");
			break;
		}
		System.out.println("exit change passoword");
		
		
	}
	
	public static Hashtable<String,String> getPasswordDict(int domain) throws Exception {
		return FileManager.readFile(FileManager.getFilePath(domain));
	}

}

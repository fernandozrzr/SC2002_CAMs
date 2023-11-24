package sc2002;

import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * A class that handle file related function
 * 
 * @author koid qian yu
 * @version 1.0
 * @since 24/11/2023
 */
public class FileManager {
	/**
	 * Static object of User
	 */
	private static User currentUser;
	/**
	 * list of file path of student and staff details
	 */
	private static String[] filePathList = new String[] {"student_list.txt","staff_list.txt"};//{"SC2002_CAMs\\SC2002\\src\\student_list.txt","SC2002_CAMs\\\\SC2002\\\\src\\staff_list.txt"};

	/**
	 * read the txt file
	 * 
	 * @param filePath file that are to be read
	 * @return a hash table of user and default password
	 */
	public static Hashtable<String, String> ReadFile(String filePath) throws FileNotFoundException {
		Hashtable<String, String> passwordDict = new Hashtable<>();
		
		passwordDict.clear();
		//System.out.println("in fm.readFile");
		Scanner input = new Scanner(new File(filePath)); // read txt file
		input.nextLine(); // to skip the first row ( title )
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] element = line.split(" ");
			element[1] = element[1].substring(0, element[1].indexOf("@"));
			
			passwordDict.put(element[1], "Password");

		}
		return passwordDict;

	}
	/**
	 * create an User object 
	 * 
	 * @param userID user id of the user object to be created
	 * @param filepath file that the user belongs to 
	 * @param domain domain that the user belongs to
	 * @return a Student object if the domain is student, a Staff object if the domain is staff.
	 */
	public static User CreateUserObject(String userID, String filepath, int domain) throws FileNotFoundException {
		
		//System.out.println("in fm.createUserObject");
		Scanner input = new Scanner(new File(filepath)); // read txt file
		input.nextLine(); // skip first line
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] element = line.split(" ");
			String userId = element[1];
			userId = userId.substring(0, userId.indexOf("@"));

			if (userId.equals(userID)) {
				
				switch(domain) {
				case 1: //student
					currentUser = new Student(element[0], userId, element[2]);
					//System.out.printf(("current user: %s %s %s\n"), element[0], userid, element[2]);
					break;
				case 2: //staff
					currentUser = new Staff(element[0], userId, element[2]);
					//System.out.printf(("current user: %s %s %s\n"), element[0], userid, element[2]);
					break;
				}
				//String name = element[0];
				//String faculty = element[2];
				break;
			}

		}
		return currentUser;
	}
	/**
	 * get the respective file path according to user domain 
	 * 
	 * @param domain domain of the user
	 * @return a file path from the filePathList according to user domain.
	 */
	public static String GetFilePath(int domain) {
		//System.out.println(filePathList[domain-1]);
	return filePathList[domain-1];
	}

}

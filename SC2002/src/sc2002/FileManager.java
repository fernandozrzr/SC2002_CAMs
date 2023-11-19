package sc2002;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class FileManager {

	private static String name;
	private static String userID;
	private static String faculty;
	private static String password;
	private static User currentUser;
	private static String[] filePathList = new String[] {"SC2002_CAMs\\SC2002\\src\\student_list.txt","SC2002_CAMs\\SC2002\\src\\staff_list.txt"};

	

	
	public static Hashtable readFile(String filePath) throws FileNotFoundException {
		Hashtable<String, String> passwordDict = new Hashtable<>();
		
		passwordDict.clear();
		System.out.println("in fm.readFile");
		Scanner input = new Scanner(new File(filePath)); // read txt file
		input.nextLine(); // to skip the first row ( title )
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] element = line.split("\t");
			//userID = element[1];
			element[1] = element[1].substring(0, element[1].indexOf("@"));
			//System.out.println(element[1]);
			passwordDict.put(element[1], "Password");

		}
		return passwordDict;

	}

	public static User createUserObject(String userID, String filepath, int domain) throws FileNotFoundException {
		
		System.out.println("in fm.createUserObject");
		Scanner input = new Scanner(new File(filepath)); // read txt file
		input.nextLine(); // skip first line
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] element = line.split("\t");
			String userid = element[1];
			userid = userid.substring(0, userid.indexOf("@"));

			// System.out.println(userid);
			if (userid.equals(userID)) {
				
				switch(domain) {
				case 1: //student
					currentUser = new Student(element[0], userid, element[2]);
					System.out.printf(("current user: %s %s %s\n"), element[0], userid, element[2]);
					break;
				case 2: //staff
					currentUser = new Staff(element[0], userid, element[2]);
					System.out.printf(("current user: %s %s %s\n"), element[0], userid, element[2]);
					break;
				}
				//String name = element[0];
				//String faculty = element[2];
				break;
			}

		}
		return currentUser;
	}
	
	public static String getFilePath(int domain) {
		//System.out.println(filePathList[domain-1]);
	return filePathList[domain-1];
	}

}

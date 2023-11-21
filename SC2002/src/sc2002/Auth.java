package sc2002;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;


public class Auth {

	// initialize the dictionary passwordDict
	private static Hashtable<String, String> studentPasswordDict = new Hashtable<>();
	private static Hashtable<String, String> staffPasswordDict = new Hashtable<>();

	private static HashMap<String, User> accounts = new HashMap<String, User>();

	public static void Init()
	{
		if(studentPasswordDict.isEmpty()==true) {
			try {
				studentPasswordDict = getPasswordDict(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(staffPasswordDict.isEmpty()==true) {
			try {
				staffPasswordDict = getPasswordDict(2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Map.Entry<String, String> entry : studentPasswordDict.entrySet()) 
		{
            String userName = entry.getKey();
			accounts.put(userName, null);
        }

		for (Map.Entry<String, String> entry : staffPasswordDict.entrySet()) 
		{
            String userName = entry.getKey();
			accounts.put(userName, null);
        }
		
		StaffController staff = new StaffController();
		staff.CreateCamp("HACHI", "21/11/2023", "25/12/2025", "SCSE", "NS", "WELCOME", "ARVI", 10, 3, true);
		staff.CreateCamp("NANA", "21/11/2023", "25/12/2025", "SCSE", "NS", "WELCOME", "ARVI", 8, 3, true);
		staff.CreateCamp("CHIIKAWA", "21/11/2023", "25/12/2025", "ADM", "NS", "WELCOME", "ARVI", 6, 2, true);
		staff.CreateCamp("USAGYUU", "21/11/2023", "28/12/2025", "SSS", "NS", "WELCOME", "ARVI", 9, 3, true);
		staff.CreateCamp("HANGYUDON", "21/11/2023", "25/12/2025", "EEE", "NS", "WELCOME", "ARVI", 10, 2, true);
		staff.CreateCamp("NANA", "21/11/2023", "25/12/2025", "NBS", "NS", "WELCOME", "ARVI", 8, 3, true);

	}

	public static void login( String userID, String password, int domain) {
		
		//System.out.println("in Auth.login");

		//check if respective passwordDict is load before 
		switch(domain) {
		case 1: //student
			// valid user
			if ( studentPasswordDict.containsKey(userID)) {
				if (studentPasswordDict.get(userID).equals(password)) {
					System.out.println("Login succesful!");

					if(accounts.get(userID) == null)
					{
						try 
						{
							camsApp.currentUser = FileManager.createUserObject(userID, FileManager.getFilePath(domain),domain);
							accounts.put(userID, camsApp.currentUser);
						} 
						catch (FileNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
						camsApp.currentUser = accounts.get(userID);
					
					// if user still using default password
					if(studentPasswordDict.get(userID).equals("Password")) {
						System.out.println("\nPlease change your password!!!");
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
			
			//passwordDict =  staffPasswordDict;
			// valid user
			if ( staffPasswordDict.containsKey(userID)) {
				if (staffPasswordDict.get(userID).equals(password)) {
					System.out.print("Login succesful!");

					if(accounts.get(userID) == null)
					{
						try 
						{
							camsApp.currentUser = FileManager.createUserObject(userID, FileManager.getFilePath(domain),domain);
							accounts.put(userID, camsApp.currentUser);
						} 
						catch (FileNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
						camsApp.currentUser = accounts.get(userID);
					
					// if user still using default password
					if(staffPasswordDict.get(userID).equals("Password")) {
						System.out.println("\nPlease change your password!!!");
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
		
		//System.out.println(studentPasswordDict);
		//System.out.println(staffPasswordDict);
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
		
		//check password security
		String strength = passwordStrength(newpass);
		
		//check the user domain 
		String type = currentUser.getClass().toString();
		type=type.substring(type.lastIndexOf("S"));
		
		//update respective passworDict
		switch(type) {
		case "Student":
			System.out.println("Change password for Student...");
			user = currentUser.getUserID();
			studentPasswordDict.put(user,newpass);
			//System.out.println(studentPasswordDict);
			break;
			
		case "Staff" :
			System.out.println("Change password for Staff...");
			user =currentUser.getUserID();
			staffPasswordDict.put(user, newpass);
			//System.out.println(staffPasswordDict);
			break;
		}
		System.out.println("Password changed successfully...");	
		System.out.println("new Password strength: " + strength);
	}
	
	public static Hashtable<String,String> getPasswordDict(int domain) throws Exception {
		return FileManager.readFile(FileManager.getFilePath(domain));
	}
	
	public static String passwordStrength(String newpass){
		
		   boolean LowerChar= false;
		   boolean UpperChar = false;
		   boolean Digit = false;
		   boolean SpecialChar = false;
		   boolean MinLength = false;
		   
		   String special_chars = "!(){}[]:;<>?,@#$%^&*+=_-~`|./'";
		   String strength;
		   char[] ch= newpass.toCharArray();
		   
		   for(int i=0; i<newpass.length(); i++){
			   if(Character.isLowerCase(ch[i])){
			       LowerChar = true;
			   }
			   if(Character.isUpperCase(ch[i])){
			       UpperChar = true;
			   }
			   if(Character.isDigit(ch[i])){
			       Digit = true;
			   }
			   if(special_chars.contains(String.valueOf(ch[i]))){
			       SpecialChar = true;
			   }
			}
			if (newpass.length() >= 6){
			   MinLength = true;
			}
			if(MinLength && Digit && UpperChar && SpecialChar && LowerChar){
				   strength = "Strong";
				} else if (MinLength && ((UpperChar && LowerChar) || Digit || SpecialChar )) {
				   strength = "Medium";
				}else{
				   strength = "Weak";
				}
			return strength;
	}

	public static void updateAcccounts(User newUser) {
		accounts.put(newUser.getUserID(), newUser);
	}
}

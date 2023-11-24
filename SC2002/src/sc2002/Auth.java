package sc2002;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/**
 * A class to do authentication of user
 * 
 * @author Koid Qian Yu
 * @version 1.0
 * @since 24/11/2023
 */
public class Auth {
	/**
	 * A hash table to store student password
	 */
	private static Hashtable<String, String> studentPasswordDict = new Hashtable<>();

	/**
	 * A hash table to store staff password
	 */
	private static Hashtable<String, String> staffPasswordDict = new Hashtable<>();

	/**
	 * A hash map to store logged in user details
	 */
	private static HashMap<String, User> accounts = new HashMap<String, User>();

	/**
	 * Initialize the hash table and create camps for demo purpose
	 */
	public static void Init()
	{
		if(studentPasswordDict.isEmpty()==true) {
			try {
				studentPasswordDict = GetPasswordDict(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(staffPasswordDict.isEmpty()==true) {
			try {
				staffPasswordDict = GetPasswordDict(2);
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
		staff.CreateCamp("POCHACCO", "24/12/2023", "20/11/2023", "SCSE", "NTU", "WELCOME", "Alexei", 8, 3, true);
		staff.CreateCamp("CHIIKAWA", "12/12/2023", "1/12/2023", "SCSE", "NS", "WELCOME", "Alexei", 6, 2, true);
		staff.CreateCamp("HACHI", "30/01/2024", "22/1/2024", "SCSE", "ECP", "WELCOME", "Alexei", 10, 3, true);
		staff.CreateCamp("KEROPPI", "2/2/2024", "18/12/2023", "EEE", "CLOUD9", "WELCOME", "Arvind", 10, 2, true);
		staff.CreateCamp("NANA", "2/1/2024", "20/12/2023", "SCSE", "HALL 6", "WELCOME", "Alexei", 8, 3, true);
		staff.CreateCamp("USAGYUU", "28/12/2023", "28/11/2023", "SCSE", "NS", "WELCOME", "Alexei", 9, 3, true);
		
		

	}

	/**
	 * verify log in of user
	 * 
	 * @param userID input UserID from user
	 * @param password input password from user
	 * @param domain input domain from user
	 */
	public static void Login( String userID, String password, int domain) {
		
		//System.out.println("in Auth.login");

		//check if respective passwordDict is load before 
		switch(domain) {
		case 1: //student
			// valid user
			if ( studentPasswordDict.containsKey(userID)) {
				if (studentPasswordDict.get(userID).equals(password)) {
					System.out.println("Login successful!");

					if(accounts.get(userID) == null)
					{
						try 
						{
							camsApp.currentUser = FileManager.CreateUserObject(userID, FileManager.GetFilePath(domain),domain);
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
					System.out.print("Login successful!");

					if(accounts.get(userID) == null)
					{
						try 
						{
							camsApp.currentUser = FileManager.CreateUserObject(userID, FileManager.GetFilePath(domain),domain);
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
	
	/**
	 * Change password of user
	 * 
	 * @param currentUser user object of current logging in user
	 */
	public static void ChangePassword(User currentUser) {
		 
		
		String user;
		Scanner sc= new Scanner(System.in);
		//display currentUser details
		System.out.println("Change Password");
		System.out.println("UserID: "+currentUser.GetUserID());
		
		//get user new password
		System.out.println("Enter new Password:\n");
		String newpass =sc.nextLine();
		
		//check password security
		String strength = PasswordStrength(newpass);
		
		//check the user domain 
		String type = currentUser.getClass().toString();
		type=type.substring(type.lastIndexOf("S"));
		
		//update respective passworDict
		switch(type) {
		case "Student":
			System.out.println("Change password for Student...");
			user = currentUser.GetUserID();
			studentPasswordDict.put(user,newpass);
			//System.out.println(studentPasswordDict);
			break;
			
		case "Staff" :
			System.out.println("Change password for Staff...");
			user =currentUser.GetUserID();
			staffPasswordDict.put(user, newpass);
			//System.out.println(staffPasswordDict);
			break;
		}
		System.out.println("Password changed successfully...");	
		System.out.println("new Password strength: " + strength);
	}
	
	/**
	 * get the respective hash table that store user's password based on domain 
	 * 
	 * @param domain input domain from user to decide return hash table 
	 * @return Hash table of respective domain
	 */
	public static Hashtable<String,String> GetPasswordDict(int domain) throws Exception {
		return FileManager.ReadFile(FileManager.GetFilePath(domain));
	}

	/**
	 * check user password strength
	 * 
	 * @param newPass string that need to be check 
	 * @return "weak" ,"medium", "strong" based on user input password
	 */
	public static String PasswordStrength(String newPass){
		
		   boolean LowerChar= false;
		   boolean UpperChar = false;
		   boolean Digit = false;
		   boolean SpecialChar = false;
		   boolean MinLength = false;
		   
		   String special_chars = "!(){}[]:;<>?,@#$%^&*+=_-~`|./'";
		   String strength;
		   char[] ch= newPass.toCharArray();
		   
		   for(int i=0; i<newPass.length(); i++){
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
			if (newPass.length() >= 6){
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

	/**
	 * update account hash map to store new user
	 * 
	 * @param newUser new user object to be added in the hash map account
	 */
	public static void UpdateAcccounts(User newUser) {
		accounts.put(newUser.GetUserID(), newUser);
	}
}

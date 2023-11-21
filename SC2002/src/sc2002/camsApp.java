package sc2002;

import java.util.*;

/*
 *  
 */

public class camsApp {

	public static User[] userList;
	public static User currentUser;
	public static int domain;
	//public static Hashtable<String, String> passwordDict = new Hashtable<>();

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		boolean a = true;

		// main loop start
		do 
		{
			//initialize currentUser
			currentUser = null;
			
			//user select domain and input validation
			do 
			{
				System.out.println("_______  _______  __   __  _______ \r\n" + "|       ||   _   ||  |_|  ||       |\r\n"
						+ "|       ||  |_|  ||       ||  _____|\r\n" + "|       ||       ||       || |_____ \r\n"
						+ "|      _||       ||       ||_____  |\r\n" + "|     |_ |   _   || ||_|| | _____| |\r\n"
						+ "|_______||__| |__||_|   |_||_______|\r\n" + "=================================================");
				System.out.println("Welcome to CAMs! \n");
				//System.out.println(currentUser);
				System.out.println("Please select your domain: \n" 
									+ "1. Student \n" 
									+ "2. Staff\n"
									+ "3. End program\n");
				try 
				{
					domain = sc.nextInt();
				} 
				catch (InputMismatchException e) 
				{
					sc.next();
					System.out.println("Invalid input");
				}
			} while (domain < 1 || domain > 3);

			if(domain==3) 
			{
				//stop the loop
				System.out.print("Ending program"); 
				a=false;
			}
			else 
			{
				System.out.println("Enter UserID: ");
				String userID = sc.next();
				System.out.println("Enter Password: ");
				String password = sc.next();

				//set currentUser after verification
				Auth.login(userID, password, domain);
				
				// user not found, loop again
				if (currentUser == null) {
					System.out.print("User Not Found");
					a = true;
				} 
				// user found, stop the loop
				else 
				{
					a = false;
				}
			}
		} while (a);


		//check current user 
		//System.out.printf("current user: %s %s %s %s\n",currentUser.getName(),currentUser.getFaculty(), currentUser.getUserID(),currentUser.getClass());
		
		//switch to respective main loop
		while(true)
		{
			switch(domain) 
			{
				case 0: // CCM
					CCMController.GetInstance().CcmMenu();
					break;

				case 1: //student 
					StudentController.GetInstance().StudentMenu();
					break;

				case 2: //staff
					StaffController.GetInstance().StaffMainLoop();
					break;

			}
		}
		
	}
}

package sc2002;

import java.util.*;

/**
 * A class that contain main 
 * 
 * @author koid qian yu
 * @version 1.0
 * @since 24/11/2023
 */
public class camsApp {

	/**
	 * Static object of User
	 */
	public static User currentUser;
	/**
	 * Static integer of domain of user
	 */
	public static int domain;
	
	/**
	 * main loop of the app
	 */
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		boolean continueApp = true;
		Auth.Init();

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
				System.out.println("Please select your domain: \n" 
									+ "1. Student \n" 
									+ "2. Staff\n"
									+ "3. End program\n");
				try 
				{
					domain = Integer.parseInt(sc.nextLine());
				} 
				catch (NumberFormatException e) 
				{
					sc.nextLine();
					System.out.println("Invalid input");
				}
			} while (domain < 1 || domain > 3);

			if(domain==3) 
			{
				//stop the loop
				System.out.print("Ending program"); 
				continueApp=false;
			}
			else 
			{
				System.out.println("Enter UserID: ");
				String userID = sc.nextLine();
				System.out.println("Enter Password: ");
				String password = sc.nextLine();

				//set currentUser after verification
				Auth.Login(userID, password, domain);
				
				// user not found, loop again
				if (currentUser == null) {
					continueApp = true;
				} 
				// user found, stop the loop
				else 
				{
					do {
						if(currentUser instanceof CCM)
							CCMController.GetInstance().CcmMenu();
						else if(currentUser instanceof Student)
							StudentController.GetInstance().StudentMenu();
						else if(currentUser instanceof Staff)
							StaffController.GetInstance().StaffMainLoop();
					}while(currentUser !=null);
				}
			}
		} while (continueApp);
	}
}

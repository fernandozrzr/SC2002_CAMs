package sc2002;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sound.midi.Soundbank;

public class StudentController 
{
    private static StudentController instance = null;
    private int enqID = 0;
    private StudentView view = null;
    private ArrayList<Camp> eligibleCamps = null;

    private StudentController()
    {
        view = new StudentView();
        eligibleCamps = new ArrayList<Camp>();
    }

    public static StudentController GetInstance()
    {
        if (instance == null)
            instance = new StudentController();

        return instance;
    }    

    // Add an enquiry
    public void AddEnquiry(int campID, String enquiry, String reply, String replyBy, Student askBy) 
    {
    	Enquiries newEnquiry = new Enquiries(enqID++, enquiry, reply, replyBy, askBy);
    	askBy.AddMyEnquiry(newEnquiry);
    	CampController.GetInstance().AddEnquiry(campID, askBy, newEnquiry);
    }
    
    // Delete an enquiry
    public void DeleteEnquiry(int campID, Student askBy, int enquiryID) 
    {
    	if (askBy.FindEnquiry(enquiryID) == null)
			System.out.println("Enquiry not found.");
		else if (askBy.FindEnquiry(enquiryID).GetStatus() == STATUS.CLOSED)
			System.out.println("Enquiry cannot be deleted as it is already processed.");
		else {
			Enquiries tempEnquiry = askBy.FindEnquiry(enquiryID);
			askBy.RemoveMyEnquiry(tempEnquiry);
			CampController.GetInstance().RemoveEnquiry(campID, askBy, tempEnquiry);
		}
    }
    
    // Edit an enquiry
    public void EditEnquiry(int campID, Student askBy, int enquiryID, String newEnquiry) 
    {
		if (askBy.FindEnquiry(enquiryID) == null)
			System.out.println("Enquiry not found.");
		else if (askBy.FindEnquiry(enquiryID).GetStatus() == STATUS.CLOSED)
			System.out.println("Enquiry cannot be edited as it is already processed.");
		else {
			int index = askBy.myEnquiries.indexOf(askBy.FindEnquiry(enquiryID));
			askBy.EditMyEnquiry(index, newEnquiry);
		}
    }

    private void InitEligibleCamps()
    {
        eligibleCamps.clear();

        for(Camp camp : CampController.GetInstance().GetCamps())
        {
            if(camp.IsVisible() && camp.GetUserGrp() == camsApp.currentUser.getFaculty())
                eligibleCamps.add(camp);
        }
    }
    
    
    ///////////////////////////////////////////////////         Main Loop Stuff         ///////////////////////////////////////////////////
    public void StudentMenu()
    {
        InitEligibleCamps();
        int choice = -1;
        Scanner input = new Scanner(System.in);

        while(choice != 0)
        {
            do //Make sure user input is correct and valid
            {
                view.DisplayMenu();
                choice = input.nextInt();
                if(choice < 0 || choice > 6)
                System.out.println("Invalid choice please try again\n");
            } while(choice < 0 || choice > 6);

            switch(choice)
            {
                case 1: // View all Camps
                {
                    // Show all the camps that student can view and options to view all details of the camp
                    System.out.print("Enter the ID of the camp you want to view details of (0 to go back): ");
                    int id = input.nextInt() - 1;

                    //Invalid Choice so exit out of case
                    if (id == 0)
                    	break;
                    else if(id < -1 || id > campIDs.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }
                    
                    view.DisplayCampDetails(id);
                    break;
                }
                
                case 2: // Register For Camp
                {
                    // Show all camps with their respective slots available beside and option to register 
                	System.out.print("Enter the ID of the camp to register: ");
                    int id = input.nextInt() - 1;

                    //Invalid Choice so exit out of case
                    if(id < 0 || id > campIDs.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    System.out.print("Would you like to join as an (1) Attendee or (2) Camp Committee Memmber: ");
                    int type = input.nextInt();

                    switch(type)
                    {
                        case 1:
                            if(camsApp.currentUser instanceof Student) {
                                CampController.GetInstance().AddAttendee(campIDs.get(id), (Student)camsApp.currentUser);
                            	((Student) camsApp.currentUser).AddRegisteredCamps(allCamps.get(id));
                            }
                            else
                                System.out.println("Invalid Request");
                            break;
                        case 2:
                            if(camsApp.currentUser instanceof Student) {
                            	Student s = (Student)camsApp.currentUser;
                            	s.ccmID = campIDs.get(id);
                            	CCM currentUser = new CCM(s.getName(), s.getUserID(), s.getFaculty(), 0, null);;
                            	camsApp.currentUser = currentUser;
                            	CampController.GetInstance().AddCommitteeMember(campIDs.get(id), (CCM)camsApp.currentUser);
                            }
                            else
                                System.out.println("You have entered an invalid choice. \n");
                            break;
                        default:
                            System.out.println("You have entered an invalid choice");
                    }
                    break;
                }
                
                case 3: // Submit Enquiries Regarding camp
                {
                    // Show All camps and options to submit enquiries
                	System.out.print("Enter the ID of the camp you want to enquire about: ");
                    int campID = input.nextInt() - 1;

                    // Check if the input is valid
                    if(campID < 0 || campID > campIDs.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }
                    else 
                    {
                    	System.out.print("Enter your enquiry: ");
                        String enquiry = input.next();
                        AddEnquiry(campID, enquiry, null, null, (Student)camsApp.currentUser);
                    }
                    
                    break;
                }
                
                case 4: // View my enquiries
                {
                    // Show all of student's enquiries and options to delete and edit them
                	view.DisplayMyEnquiries(((Student)camsApp.currentUser).GetMyEnquiries());
                	
                	System.out.print("Would you like to (1) Edit an enquiry or (2) Delete an enquiry (0 to go back): ");
                    int temp = input.nextInt() - 1;

                    // Go back
                    if (temp == 0)
                    	break;
                    	
                    // Edit an enquiry
                    else if (temp == 1){
                    	System.out.print("Enter the campID of the enquiry you want to edit: ");
                        int campID = input.nextInt();
                    	
                    	System.out.print("Enter the enquiry you want to edit: ");
                        int enquiryID = input.nextInt();
                        
                        System.out.print("Enter the edited enquiry: ");
                        String newEnquiry = input.next();
                        
                        EditEnquiry(campID,(Student)camsApp.currentUser, enquiryID, newEnquiry);
                        
                        break;
                    }
                    
                    // Remove an enquiry
                    else if (temp == 2) {
                    	System.out.print("Enter the campID of the enquiry you want to edit: ");
                    	int campID = input.nextInt();
                        
                    	System.out.print("Enter the enquiry you want to delete: ");
                    	int enquiryID = input.nextInt();
                        
                        DeleteEnquiry(campID, (Student)camsApp.currentUser, enquiryID);
                        break;
                    }
                    
                    // Invalid input
                    else{
                    	System.out.println("You have entered an invalid choice. \n");
                        break;
                    }
                }
                
                case 5: // View my profile
                {
                    System.out.println("Name: " + camsApp.currentUser.getName() + "\n");
                    System.out.println("ID: " + camsApp.currentUser.getUserID() + "\n");
                    System.out.println("Faculty: " + camsApp.currentUser.getFaculty() + "\n");
                    break;
                }
                
                case 6: // View registered camps
                {
                	// Show camps that they registered for and roles (attendee or ccm)
                	view.DisplayMyCamps(((Student)camsApp.currentUser).GetRegisteredCamps());
                	
                	// Withdraw from camp
                	System.out.print("Enter the ID of the camp to withdraw from (0 to go back): ");
                    int id = input.nextInt() - 1;

                    // Check if the input is valid
                    if (id == 0)
                    	break;
                    else if(id < -1 || id > campIDs.size()){
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }
                    
                    // If user is camp committee member, not allowed to withdraw from camp
                    else if (id == ((Student)camsApp.currentUser).ccmID) {
                    	System.out.println("You are not allowed to withdraw. \n");
                    }
                    
                    // Withdraw from camp
                    else{
                        CampController.GetInstance().RemoveAttendee(campIDs.get(id), (Student)camsApp.currentUser);
                    	((Student) camsApp.currentUser).RemoveRegisteredCamps(allCamps.get(id));
                    }

                	break;
                }
                default:
            }
        }
        
    }

    private void CampMenu()
    {
        String[] choices = {"view all", "view registered",  "view details", "register", "withdraw", "exit"};
        String choice = "";
        boolean exit = false;

        Scanner sc = new Scanner(System.in);
        
        while(!exit)
        {
            //Printing out the options and checking if validity
            do
            {
                System.out.println("\"" + choices[0] + "\" to view all camps");
                System.out.println("\"" + choices[1] + "\" to view registered camps");
                System.out.println("\"" + choices[2] + "\" to view a camp's details");
                System.out.println("\"" + choices[3] + "\" to register for a camp");
                System.out.println("\"" + choices[4] + "\" to withdraw from a camp");
                System.out.println("\"" + choices[5] + "\" to exit this page");
                System.out.print("Please enter your choice as stated above: ");

                try
                {
                    choice = sc.nextLine();
                    choice = choice.toLowerCase();
                }
                catch(InputMismatchException e) 
                {
                    System.out.println("Invalid Option!");
                }
                
            }while(!IsValidChoice(choice, choices));

            //Execute function based on choice
            switch (choice) 
            {
                case "view all":
                {
                    view.DisplayAllCamps();
                    break;
                }

                case "view registered":
                {
                    Student user = null;

                    if(camsApp.currentUser instanceof Student)
                    {
                        user = (Student)(camsApp.currentUser);
                    }
                    else
                    {
                        System.out.println("Invalid request, User is not a Student");
                        break;
                    }

                    view.DisplayMyCamps(user.GetRegisteredCamps());
                    break;
                }
                
                case "view details":
                {
                    System.out.print("Enter the ID of the camp you want to view details of: ");
                    int campID = -1;

                    //Error Checking
                    try
                    {
                        campID = sc.nextInt();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Option!");
                    }

                    view.DisplayCampDetails(campID);

                    break;
                }
                
                case "register":
                {
                    // Show all camps with their respective slots available beside and option to register 
                    int campIndex = -1;
                	System.out.print("Enter the ID of the camp to register: ");

                    try
                    {
                        campIndex = sc.nextInt();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Option!");
                    }

                    //Invalid Choice so exit out of case
                    if(campIndex < 0 || campIndex > eligibleCamps.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    int type = -1;
                    System.out.print("Would you like to join as an (1) Attendee or (2) Camp Committee Memmber: ");

                    try
                    {
                        type = sc.nextInt();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Option!");
                    }

                    switch(type)
                    {
                        case 1:
                            if(camsApp.currentUser instanceof Student) 
                            {
                            	((Student) camsApp.currentUser).AddRegisteredCamps(eligibleCamps.get(campIndex));
                                CampController.GetInstance().AddAttendee(eligibleCamps.get(campIndex).GetCampID(), (Student)camsApp.currentUser);
                            }
                            else
                                System.out.println("Invalid Request, User is not a Student");
                            break;
                        case 2:
                            if(camsApp.currentUser instanceof Student) 
                            {
                            	Student s = (Student)camsApp.currentUser;
                            	s.ccmID = eligibleCamps.get(campIndex).GetCampID();
                                
                            	CCM currentUser = new CCM(s.getName(), s.getUserID(), s.getFaculty(), s.GetMyEnquiries(), s.GetRegisteredCamps(), s.GetccmID();
                            	camsApp.currentUser = currentUser;
                            	CampController.GetInstance().AddCommitteeMember(currentUser.ccmID, currentUser);
                            }
                            else
                                System.out.println("You have entered an invalid choice, User is not a Student \n");
                            break;
                        default:
                            System.out.println("You have entered an invalid choice");
                    }
                    break;
                }

                case "withdraw":
                {
                    int campIndex = -1;
                	System.out.print("Enter the ID of the camp to register: ");

                    try
                    {
                        campIndex = sc.nextInt();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Option!");
                    }

                    //Invalid Choice so exit out of case
                    if(campIndex < 0 || campIndex > eligibleCamps.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }
                    
                    if(camsApp.currentUser instanceof Student)
                    {
                        Student s = (Student)camsApp.currentUser;
                        CampController.GetInstance().RemoveAttendee(eligibleCamps.get(campIndex).GetCampID(), s);
                        s.RemoveRegisteredCamps(eligibleCamps.get(campIndex));
                    }
                    else if(camsApp.currentUser instanceof CCM)
                    {
                        System.out.println("You have entered an invalid choice, User is not a Student \n");
                    }

                    break;
                }

                case "exit":
                {
                    exit = true;
                    break;
                }
            
                default:
                    break;
            }
        }
    }

    private void EnquiriesMenu()
    {
        String[] choices = {"view", "edit",  "delete", "submit", "exit"};
        String choice = "";
        boolean exit = false;

        Scanner sc = new Scanner(System.in);
        
        while(!exit)
        {
            //Printing out the options and checking if validity
            do
            {
                System.out.println("\"" + choices[0] + "\" to view enquires submitted");
                System.out.println("\"" + choices[1] + "\" to edit an enquriy");
                System.out.println("\"" + choices[2] + "\" to delete an enquiry");
                System.out.println("\"" + choices[3] + "\" to submit an enquiry");
                System.out.println("\"" + choices[4] + "\" to exit this page");
                System.out.print("Please enter your choice as stated above: ");

                try
                {
                    choice = sc.nextLine();
                    choice = choice.toLowerCase();
                }
                catch(InputMismatchException e) 
                {
                    System.out.println("Invalid Option!");
                }
                
            }while(!IsValidChoice(choice, choices));

            switch (choice) {
                case "view":
                {

                    break;
                }

                case "edit":
                {

                    break;
                }

                case "delete":
                {

                    break;
                }

                case "submit":
                {

                    break;
                }

                case "exit":
                {
                    exit = true;
                    break;
                }
            
                default:
                    break;
            }
        }
    }

    private void ProfileMenu()
    {
        System.out.println("Name: " + camsApp.currentUser.getName());
        System.out.println("ID: " + camsApp.currentUser.getUserID());
        System.out.println("Faculty: " + camsApp.currentUser.getFaculty());
        System.out.println("You are not registered as a Committee Member in any camp");
    }

    protected boolean IsValidChoice(String userChoice, String[] choices)
    {
        for(String choice : choices)
        {
            if(userChoice == choice) return true;
        }

        return false;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

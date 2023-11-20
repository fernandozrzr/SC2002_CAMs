package sc2002;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentController 
{
    private static StudentController instance = null;
    private int enqID = 0;
    private StudentView view = null;
    StudentController()
    {
        view = new StudentView();
    }

    public static StudentController GetInstance()
    {
        if (instance == null)
            instance = new StudentController();

        return instance;
    }
    
    public ArrayList<Camp> allCamps = CampController.GetInstance().GetCamps();
    public ArrayList<Integer> campIDs = view.DisplayAllCamps();
    
    // Add an enquiry
    public void AddEnquiry(int campID, String enquiry, String reply, String replyBy, Student askBy, int status) {
    	Enquiries newEnquiry = new Enquiries(enqID++, enquiry, reply, replyBy, askBy, status);
    	askBy.AddMyEnquiry(newEnquiry);
    	CampController.GetInstance().AddEnquiry(campID, askBy, newEnquiry);
    }
    
    // Delete an enquiry
    public void DeleteEnquiry(int campID, Student askBy, int enquiryID) {
    	if (askBy.FindEnquiry(enquiryID) == null)
			System.out.println("Enquiry not found.");
		else if (askBy.FindEnquiry(enquiryID).GetStatus() == 1)
			System.out.println("Enquiry cannot be deleted as it is already processed.");
		else {
			Enquiries tempEnquiry = askBy.FindEnquiry(enquiryID);
			askBy.RemoveMyEnquiry(tempEnquiry);
			CampController.GetInstance().RemoveEnquiry(campID, askBy, tempEnquiry);
		}
    }
    
    // Edit an enquiry
    public void EditEnquiry(int campID, Student askBy, int enquiryID, String newEnquiry) {
		if (askBy.FindEnquiry(enquiryID) == null)
			System.out.println("Enquiry not found.");
		else if (askBy.FindEnquiry(enquiryID).GetStatus() == 1)
			System.out.println("Enquiry cannot be edited as it is already processed.");
		else {
			int index = askBy.myEnquiries.indexOf(askBy.FindEnquiry(enquiryID));
			askBy.EditMyEnquiry(index, newEnquiry);
		}
    }
    
    
    ///////////////////////////////////////////////////         Main Loop Stuff         ///////////////////////////////////////////////////
    public void StudentMenu()
    {
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
                    
                    else {
                    	System.out.print("Enter your enquiry: ");
                        String enquiry = input.next();
                        AddEnquiry(campID, enquiry, null, null, (Student)camsApp.currentUser, 0);
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
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

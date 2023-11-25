package sc2002;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Controller class which controls all the logic related to students
 * 
 * @author Kiersten Yeo
 * @version 1.0
 * @since 25/11/2023
 */
public class StudentController 
{
	/**
	 * Static instance of StudentController
	 */
    	private static StudentController instance = null;
    	/**
	 * Instance of StudentView
	 */
    	private StudentView view = null;
    	/**
	 * List of camps the student is eligible for
	 */
    	private ArrayList<Camp> eligibleCamps = null;

    	/**
	 * Default Constructor of StudentController
     	 * Initialises list of eligible camps
	 * 
	 */
    	private StudentController()
    	{
        	view = new StudentView();
        	eligibleCamps = new ArrayList<Camp>();
    	}
    	/**
     	* Gets the instance of the StudentController, creating one if it does not already exist
     	* 
     	* @return StudentController instance
     	*/
    	public static StudentController GetInstance()
    	{
        	if (instance == null)
            	instance = new StudentController();

        	return instance;
    	}    

    	/**
     	* Adds an enquiry to the specified camp and student.
     	* 
     	* @param campID ID of the camp
     	* @param enquiry Enquiry to be added
     	* @param reply Reply to the enquiry
     	* @param replyBy CCM or Staff who replied to the enquiry
     	* @param askBy Student to add the enquiry to
     	*/
    	public void AddEnquiry(int campID, String enquiry, String reply, String replyBy, Student askBy) 
    	{
    		Enquiries newEnquiry = new Enquiries(campID, enquiry, reply, replyBy, askBy);
    		askBy.AddMyEnquiry(newEnquiry);
    		CampController.GetInstance().AddEnquiry(campID, askBy, newEnquiry);
    	}
    	/**
     	* Deletes an enquiry from the specified camp and student.
     	* 
     	* @param askBy Student to remove the enquiry from
     	* @param enquiryID ID of the enquiry to be removed
     	*/
    	public void DeleteEnquiry(Student askBy, int enquiryID) 
    	{
    		if (askBy.FindEnquiry(enquiryID) == null)
			System.out.println("Enquiry not found.");
		else if (askBy.FindEnquiry(enquiryID).GetStatus() == STATUS.CLOSED)
			System.out.println("Enquiry cannot be deleted as it is already processed.");
		else {
			Enquiries tempEnquiry = askBy.FindEnquiry(enquiryID);
			askBy.RemoveMyEnquiry(tempEnquiry);
			CampController.GetInstance().RemoveEnquiry(tempEnquiry.GetCampID(), askBy, tempEnquiry);
		}
    	}
    
    	/**
     	* Edits an enquiry of the specified camp and student.
     	* 
     	* @param askBy Student editing the enquiry
     	* @param enquiryID ID of the enquiry to be edited
     	* @param newEnquiry The edited enquiry to replace the old enquiry
     	*/
    	public void EditEnquiry(Student askBy, int enquiryID, String newEnquiry) 
    	{
		if (askBy.FindEnquiry(enquiryID) == null)
			System.out.println("Enquiry not found.");
		else if (askBy.FindEnquiry(enquiryID).GetStatus() == STATUS.CLOSED)
			System.out.println("Enquiry cannot be edited as it is already processed.");
		else {
			askBy.EditMyEnquiry(enquiryID, newEnquiry);
		}
    	}

    	/**
	 * Creates list of eligible camps for current user
	 * 
	 */
    	private void InitEligibleCamps()
    	{
    		eligibleCamps.clear();

        	for(Camp camp : CampController.GetInstance().GetCamps())
        	{
            		if(camp == null) continue;
            
            		if(camp.IsVisible() && camp.GetUserGrp().equals(camsApp.currentUser.GetFaculty()))
                		eligibleCamps.add(camp);
        	}
    	}
    
    
    ///////////////////////////////////////////////////         Main Loop Stuff         ///////////////////////////////////////////////////
    /**
     * Student main menu for handling various operations.
     */
    public void StudentMenu()
    {
        InitEligibleCamps();
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        boolean becameCCM = false;

        while(choice != 0)
        {
            view.DisplayMainMenu();
            try
            {
                choice = Integer.parseInt(sc.nextLine());
            }
            catch(InputMismatchException e) 
            {
                System.out.println("Invalid Input!");
                break;
            }

            switch (choice) {
            	case 0: 
            		camsApp.currentUser=null;
            		break;
                case 1:
                    ProfileMenu();
                    break;
                case 2:
                    becameCCM = CampMenu();
                    break;
                case 3:
                    EnquiriesMenu();
                    break;
            
                default:
                    break;
            }

            if(becameCCM) return;
        }
        
    }
    /**
     * Student camp menu for handling various operations related to camps.
     */
    private boolean CampMenu()
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
                System.out.println();
                System.out.println("/////////////////////////////////////////////////////////////////////////");
                System.out.println("///////////////////////        Camp Menu        ////////////////////////");
                System.out.println("/////////////////////////////////////////////////////////////////////////");
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
                    System.out.println("Invalid Input!");
                    break;
                }
                
            }while(!Utility.IsValidChoice(choice, choices));

            System.out.println("/////////////////////////////////////////////////////////////////////////");
            System.out.println();
            //Execute function based on choice
            switch (choice) 
            {
                case "view all":
                {
                    
                    view.DisplayAllCamps(CampView.GetFilter());
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
                        campID = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
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
                        campIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    //Invalid Choice so exit out of case
                    if(campIndex < 0 || campIndex >= eligibleCamps.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    if(!eligibleCamps.get(campIndex).CanRegister())
                    {
                        System.out.println("The registeration date has passed.");
                        break;
                    }
                    
                    if(CampController.GetInstance().AlreadyRegistered(campIndex, camsApp.currentUser.GetName()))
                    {
                        System.out.println("You are already registered in this camp.");
                        break;
                    }
                    if(CampController.GetInstance().AlreadyCommittee(campIndex, camsApp.currentUser.GetName()))
                    {
                        System.out.println("You are already registered in this camp.");
                        break;
                    }
                    
                    //System.out.println("date:"+eligibleCamps.get(campIndex).GetDate()+"_");
                    if(((Student) camsApp.currentUser).dateClash(eligibleCamps.get(campIndex).GetDate())) {
                    	System.out.println("Date of camp clashes with one of your registered camps.");
                    	break;
                    }

                    int type = -1;
                    System.out.print("Would you like to join as an (1) Attendee or (2) Camp Committee Memmber: ");

                    try
                    {
                        type = Integer.parseInt(sc.nextLine());
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
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
                                System.out.println("Invalid request, User is not a Student");
                            break;
                        case 2:
                            if(camsApp.currentUser instanceof Student) 
                            {
                            	Student s = (Student)camsApp.currentUser;
                            	s.ccmID = eligibleCamps.get(campIndex).GetCampID();
                                
                            	CCM currentUser = new CCM(s.GetName(), s.GetUserID(), s.GetFaculty(), s.GetMyEnquiries(), s.GetRegisteredCamps(), s.GetccmID());
                            	camsApp.currentUser = currentUser;
                            	CampController.GetInstance().AddCommitteeMember(currentUser.ccmID, currentUser);
                            	Auth.UpdateAcccounts(currentUser);
                                exit = true;
                                camsApp.domain = 0;
                                return true;
                            }
                            else
                                System.out.println("Invalid request, User is not a Student");
                            break;
                        default:
                            System.out.println("You have entered an invalid choice");
                    }
                    break;
                }

                case "withdraw":
                {
                    int campIndex = -1;
                	System.out.print("Enter the ID of the camp to withdraw: ");

                    try
                    {
                        campIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
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
                        System.out.println("You have successfully withdrawn from the camp and will not be allowed to join back");
                    }
                    else if(camsApp.currentUser instanceof CCM)
                    {
                        System.out.println("Invalid request, User is not a Student");
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

        return false;
    }
    /**
     * Student enquiries menu for handling various operations related to enquiries.
     */
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
                System.out.println();
                System.out.println("/////////////////////////////////////////////////////////////////////////");
                System.out.println("/////////////////////        Enquiries Menu        //////////////////////");
                System.out.println("/////////////////////////////////////////////////////////////////////////");
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
                    System.out.println("Invalid Input!");
                    break;
                }
                
            }while(!Utility.IsValidChoice(choice, choices));

            System.out.println("/////////////////////////////////////////////////////////////////////////");
            System.out.println();

            switch (choice) {
                case "view":
                {
                    if(camsApp.currentUser instanceof Student)
                        view.DisplayMyEnquiries(((Student)camsApp.currentUser).GetMyEnquiries());
                    else
                        System.out.println("Invalid request, User is not a Student");
                    break;
                }

                case "edit":
                {
                    int enquiryIndex = -1;
                	System.out.print("Enter the ID of the enquiry to edit: ");

                    try
                    {
                        enquiryIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(enquiryIndex < 0 || enquiryIndex > ((Student)camsApp.currentUser).GetMyEnquiries().size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    String enquiry = "";
                    try
                    {
                        enquiry = sc.nextLine();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(camsApp.currentUser instanceof Student)
                    {
                        EditEnquiry((Student)camsApp.currentUser, enquiryIndex, enquiry);
                        System.out.println("Enquiry edited successfully");
                    }
                    else
                        System.out.println("Invalid request, User is not a Student");
                    break;
                }

                case "delete":
                {
                    int enquiryIndex = -1;
                	System.out.print("Enter the ID of the enquiry to delete: ");

                    try
                    {
                        enquiryIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(enquiryIndex < 0 || enquiryIndex > ((Student)camsApp.currentUser).GetMyEnquiries().size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    if(camsApp.currentUser instanceof Student)
                    {
                        DeleteEnquiry((Student)camsApp.currentUser, enquiryIndex);
                        System.out.println("Enquiry deleted successfully");
                    }
                    else
                        System.out.println("Invalid request, User is not a Student");
                    break;
                }

                case "submit":
                {
                    int campIndex = -1;
                    view.DisplayAllCamps(false);
                	System.out.print("Enter the ID of the camp you want to enquire about: ");

                    try
                    {
                        campIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(campIndex < 0 || campIndex > eligibleCamps.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }
                   
                    String enquiry = "";
                    try
                    {
                    	System.out.println("Enquiries: ");
                        enquiry = sc.nextLine();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(camsApp.currentUser instanceof Student)
                    {
                        AddEnquiry(eligibleCamps.get(campIndex).GetCampID(), enquiry, null, null, (Student)camsApp.currentUser);
                        System.out.println("Enquiry successfully submitted");
                    }
                    else
                        System.out.println("Invalid request, User is not a Student");
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
    /**
     * Student profile menu for handling various operations related to the student's profile.
     */
    private void ProfileMenu()
    {
        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.println();
        System.out.println("Name: " + camsApp.currentUser.GetName());
        System.out.println("ID: " + camsApp.currentUser.GetUserID());
        System.out.println("Faculty: " + camsApp.currentUser.GetFaculty());
        System.out.println("You are not registered as a Committee Member in any camp");
        System.out.println();
    }

    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

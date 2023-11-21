package sc2002;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


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
    	Enquiries newEnquiry = new Enquiries(enqID++, campID, enquiry, reply, replyBy, askBy);
    	askBy.AddMyEnquiry(newEnquiry);
    	CampController.GetInstance().AddEnquiry(campID, askBy, newEnquiry);
    }
    
    // Delete an enquiry
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
    
    // Edit an enquiry
    public void EditEnquiry(Student askBy, int enquiryID, String newEnquiry) 
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
            view.DisplayMainMenu();
            try
            {
                choice = input.nextInt();
            }
            catch(InputMismatchException e) 
            {
                System.out.println("Invalid Input!");
                break;
            }

            switch (choice) {
                case 1:
                    ProfileMenu();
                    break;
                case 2:
                    CampMenu();
                    break;
                case 3:
                    EnquiriesMenu();
                    break;
            
                default:
                    break;
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
                        System.out.println("Invalid Input!");
                        break;
                    }

                    view.DisplayCampDetails(campID, (Student)camsApp.currentUser);

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
                        System.out.println("Invalid Input!");
                        break;
                    }

                    //Invalid Choice so exit out of case
                    if(campIndex < 0 || campIndex > eligibleCamps.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    if(!eligibleCamps.get(campIndex).CanRegister())
                    {
                        System.out.println("The registeration date has passed.");
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
                                
                            	CCM currentUser = new CCM(s.getName(), s.getUserID(), s.getFaculty(), s.GetMyEnquiries(), s.GetRegisteredCamps(), s.GetccmID());
                            	camsApp.currentUser = currentUser;
                            	CampController.GetInstance().AddCommitteeMember(currentUser.ccmID, currentUser);
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
                	System.out.print("Enter the ID of the camp to register: ");

                    try
                    {
                        campIndex = sc.nextInt();
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
                
            }while(!IsValidChoice(choice, choices));

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
                        enquiryIndex = sc.nextInt() - 1;
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
                        EditEnquiry((Student)camsApp.currentUser, enquiryIndex, choice);
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
                        enquiryIndex = sc.nextInt() - 1;
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
                        DeleteEnquiry((Student)camsApp.currentUser, enquiryIndex);
                    else
                        System.out.println("Invalid request, User is not a Student");
                    break;
                }

                case "submit":
                {
                    int campIndex = -1;
                    view.DisplayAllCamps();
                	System.out.print("Enter the ID of the camp you want to enquire about: ");

                    try
                    {
                        campIndex = sc.nextInt() - 1;
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
                        enquiry = sc.nextLine();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(camsApp.currentUser instanceof Student)
                        AddEnquiry(eligibleCamps.get(campIndex).GetCampID(), enquiry, null, null, (Student)camsApp.currentUser);
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
            if(userChoice.equals(choice)) return true;
        }

        return false;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

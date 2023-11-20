package sc2002;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentController 
{
    private static StudentController instance = null;

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
            }while(choice < 0 || choice > 6);

            switch(choice)
            {
                case 1: // View all Camps
                {
                    // Show all the camps that student can view and options to view all details of the camp
                    ArrayList<Integer> campIndexes = view.DisplayAllCamps();
                    System.out.print("Enter the index of the camp of the details you want to view(0 to go back): ");
                    int index = input.nextInt() - 1;

                    //Invalid Choice so exit out of case
                    if(index < 0 || index > campIndexes.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }
                    
                    view.DisplayCampDetails(index);
                    break;
                }
                case 2: // Register For Camp
                {
                    // Show all camps with their respective slots available beside and option to register 
                    ArrayList<Integer> campIDs = view.DisplayAllCamps();
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
                            	((Student) camsApp.currentUser).AddRegisteredCamps(id);
                            	// SORRY still figuring this out: need to add a Camp object to myCamps
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
                                System.out.println("Invalid Request");
                            break;
                        default:
                            System.out.println("You have entered an invalid choice");
                    }
                    break;
                }
                case 3: // Submit Enquires Regarding camp
                {
                    // Show All camps and options to submit enquires
                    ArrayList<Integer> campIDs = view.DisplayAllCamps();

                    System.out.print("Enter the id of the camp to enquire: ");
                    int id = input.nextInt() - 1;

                    //Check if the input is valid
                    if(id < 0 || id > campIDs.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    System.out.print("Enter your enquiry: ");
                    String enquiry = input.next();
                    
                    // SORRY still figuring the Enquiry stuff out
                    // AddEnquiry(index, (Student)camsApp.currentUser, enquiry);
                    break;
                }
                case 4: // View my enquires
                {
                    // Show all of student's enquires and options to delete and edit them
                	view.DisplayMyEnquiries(((Student)camsApp.currentUser).myEnquiries);
                    break;
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
                    // Show camps that they registered for, roles (attendee or ccm) and option to withdraw from camp
                	view.DisplayMyCamps(((Student)camsApp.currentUser).registeredCamps);
                    break;
                }
                default:
            }
        }
        
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

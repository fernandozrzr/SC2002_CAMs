package sc2002;

import java.util.Scanner;

public class StudentController 
{
    private static StudentController instance = null;

    private StudentView view = null;

    private StudentController()
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
                    // Show all the camps that student can view and options to view all details of the camp
                    view.DisplayAllCamps();
                    break;

                case 2: // Register For Camp
                    // Show all camps with their respective slots available beside and option to register 
                    break;

                case 3: // Submit Enquires Regarding camp
                    // Show All camps and options to submit enquires
                    break;

                case 4: // View my enquires
                    // Show all of student's enquires and options to delete and edit them
                    break;

                case 5: // View my profile
                    // Show whether student is CCM
                    break;

                case 6: // View registered camps
                    // Show camps that they registered for, roles (attendee or ccm) and option to withdraw from camp
                    break;

                default:
            }
        }
        
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

package sc2002;

import java.util.ArrayList;

public class StudentView implements CampView
{
    public StudentView()
    {
    	super();
    }

    ///////////////////////////////////////////////////         Implmenting interface functions         ///////////////////////////////////////////////////
    @Override
    public void DisplayAllCamps()
    {
        ArrayList<Camp> allCamps = CampController.GetInstance().GetCamps();
        
        int x = 0;
        
        for(int i = 0; i < allCamps.size(); ++i)
        {
            if (allCamps.get(i).IsVisible() && allCamps.get(i).GetUserGrp() == camsApp.currentUser.getFaculty())
            {
            	System.out.println("(" + (x + 1) + ") " + allCamps.get(i).GetCampName());
            	++x;
            }
        }
        
    }

    @Override
    public void DisplayMyCamps(ArrayList<Camp> camp) 
    {
        if(camp.size() == 0)
        {
            System.out.println("You are not registerd in any camps");
            return;
        }

    	for (Camp campTemp : camp) 
            System.out.println("Registered for Camp " + campTemp.GetCampName() + " as an attendee.");
    }

    public void DisplayMyEnquiries(ArrayList<Enquiries> enquries) 
    {
        for(int i = 0; i < enquries.size(); ++i)
        {
            
        }
        for (Enquiries enquiryTemp : enquiry) 
        {
            System.out.println();
            System.out.println(enquiryTemp); 
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////         Main Loop Stuff         /////////////////////////////////////////////////////////////////////
    public void DisplayMainMenu()
    {
        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.println("//////////////////////        Student Menu        ///////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////////////");

        System.out.println("(1) View All Camps");// Show all the camps that student can view and options to view all details of the camp
        System.out.println("(2) Register For Camp"); // Show all camps with their respective slots available beside and option to register 
        System.out.println("(3) Submit Enquires Regarding Camp");// Show All camps and options to submit enquires
        System.out.println("(4) View My Enquires"); // Show all of student's enquires and options to delete and edit them
        System.out.println("(5) View My Profile"); // Show whether student is CCM
        System.out.println("(6) View Registered Camps"); // Show camps that they registered for, roles (attendee or ccm) and option to withdraw from camp

        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.print("Enter your Choice: ");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

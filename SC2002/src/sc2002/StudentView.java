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
    public ArrayList<Integer> DisplayAllCamps()
    {
        ArrayList<Camp> allCamps = CampController.GetInstance().GetCamps();
        ArrayList<Integer> eligibleCamps = new ArrayList<Integer>();
        
        int x = 0;
        
        for(int i = 0; i < allCamps.size(); ++i)
        {
            if (allCamps.get(i).IsVisible() == true && allCamps.get(i).GetUserGrp() == camsApp.currentUser.getFaculty())
            	eligibleCamps.add(allCamps.get(i).GetCampID());
            	System.out.println("(" + (x + 1) + ") " + allCamps.get(i).GetCampName());
            	x++;
        }
        
        return eligibleCamps;
    }

    @Override
    public void DisplayMyCamps(ArrayList<Camp> camp) 
    {
    	for (Camp campTemp : camp) {
    		if (campTemp.GetCampID() == ((Student)camsApp.currentUser).ccmID)
    			System.out.println("Registered for Camp " + campTemp.GetCampName() + " as camp committee member.");
    		else
    			System.out.println("Registered for Camp " + campTemp.GetCampName() + " as attendee.");
        }
    }

    public void DisplayMyEnquiries(ArrayList<Enquiries> enquiry) 
    {
        for (Enquiries enquiryTemp : enquiry) {
            System.out.println(enquiryTemp); 
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////         Main Loop Stuff         /////////////////////////////////////////////////////////////////////
    public void DisplayMenu()
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

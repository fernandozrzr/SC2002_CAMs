package sc2002;

import java.util.ArrayList;

public class StudentView implements CampView, EnquiriesView
{
    public StudentView()
    {

    }

    ///////////////////////////////////////////////////         Implmenting interface functions         ///////////////////////////////////////////////////
    @Override
    public ArrayList<Integer> DisplayAllCamps()
    {
        // TODO Auto-generated method stub
        //
        return null;
    }

    @Override
    public void DisplayMyCamps(ArrayList<Camp> camp) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void DisplayAllEnquiries(Camp camp) 
    {
        // TODO Auto-generated method stub
        
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

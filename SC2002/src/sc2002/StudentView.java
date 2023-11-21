package sc2002;

import java.util.ArrayList;
import java.util.Map;

public class StudentView implements CampView
{
    public StudentView()
    {
    	super();
    }

    ///////////////////////////////////////////////////         Implmenting interface functions         ///////////////////////////////////////////////////
//    @Override
//    public void DisplayAllCamps()
//    {
//        ArrayList<Camp> allCamps = CampController.GetInstance().GetCamps();
//        
//        int x = 0;
//        
//        for(int i = 0; i < allCamps.size(); ++i)
//        {
//            if (allCamps.get(i).IsVisible() && camsApp.currentUser.getFaculty().equals(allCamps.get(i).GetUserGrp()))
//            {
//            	System.out.println("(" + (x + 1) + ") " + allCamps.get(i).GetCampName());
//                int slotsLeft = allCamps.get(i).GetTotalSlots() - allCamps.get(i).GetAttendees().size();
//                System.out.println("\tTotal Slots Available: " + slotsLeft + "/" + allCamps.get(i).GetTotalSlots());
//                slotsLeft = allCamps.get(i).GetCommitteeSlots() - allCamps.get(i).GetCommitteeMembers().size();
//                System.out.println("\tCommittee Member Slots Available: " + slotsLeft + "/" + allCamps.get(i).GetCommitteeSlots());
//            	++x;
//            }
//        }
//        
//    }

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

    public void DisplayMyEnquiries(ArrayList<Enquiries> enquiries) 
    {
        ArrayList<Camp> campList = CampController.GetInstance().GetCamps();
        int count = 1;

        for(Enquiries enquiry : enquiries)
        {
            System.out.println("(" + (count++) + ") " + enquiry.GetEnquiry() + " [" + enquiry.GetStatus() + ", " + campList.get(enquiry.GetCampID()).GetCampName() + "]");
            if(enquiry.GetStatus() == STATUS.CLOSED)
                System.out.println("\t\t" + "Answer: " + enquiry.GetReply());
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////         Main Loop Stuff         /////////////////////////////////////////////////////////////////////
    public void DisplayMainMenu()
    {
        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.println("//////////////////////        Student Menu        ///////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////////////");

        System.out.println("(0) Log Out");
        System.out.println("Profile");
        System.out.println("\t(1) View Profile");
        System.out.println("Camps");
        System.out.println("\t(2) View / Register / Withdraw");
        System.out.println("Enquiries");
        System.out.println("\t(3) View / Edit / Delete / Submit");

        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.print("Enter your Choice: ");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

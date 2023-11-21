package sc2002;

import java.util.ArrayList;

public interface CampView 
{
    public default void DisplayAllCamps()
    {
        ArrayList<Camp> camps = CampController.GetInstance().GetCamps();
        for(int i = 0; i < camps.size(); ++i)
            System.out.println("(" + (i + 1) + ") " + camps.get(i).GetCampName());
    }

    public default void DisplayCampDetails(int campID, Student student) 
    {
        ArrayList<Camp> campList = CampController.GetInstance().GetCamps();

        if(campID < 0 || campID > campList.size())
        {
            System.out.println("Not a valid CampID");
            return;
        }

        Camp camp = campList.get(campID);
        if(camp == null)
        {
            System.out.println("Not a valid CampID");
            return;
        }

        if(!camp.GetAttendees().contains(student))
        {
            System.out.println("You are not enrolled in this camp");
            return;
        }

        System.out.println("Camp Name: " + camp.GetCampName());
        System.out.println("Date: " + camp.GetDate());
        System.out.println("Registration Dateline: " + camp.GetRegisterCloseDate());
        System.out.println("Camp Group: " + camp.GetUserGrp());
        System.out.println("Location: " + camp.GetLocation());
        System.out.println("Description: " + camp.GetDescription());
        System.out.println("Staff In Charge: " + camp.GetStaffInCharge());

        int slotsLeft = camp.GetTotalSlots() - camp.GetAttendees().size();
        System.out.println("Total Slots Available: " + slotsLeft + "/" + camp.GetTotalSlots());
        slotsLeft = camp.GetCommitteeSlots() - camp.GetCommitteeMembers().size();
        System.out.println("Committee Member Slots Available: " + slotsLeft + "/" + camp.GetCommitteeSlots());
        System.out.println();
    }
	
    public void DisplayMyCamps(ArrayList<Camp> camps);
   
}
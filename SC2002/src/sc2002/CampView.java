package sc2002;

import java.util.ArrayList;

public interface CampView 
{
    public default ArrayList<Integer> DisplayAllCamps()
    {
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        ArrayList<Camp> camps = CampController.GetInstance().GetCamps();
        for(int i = 0; i < camps.size(); ++i)
        {
            System.out.println("(" + (i + 1) + ") " + camps.get(i).GetCampName());
            indexes.add(camps.get(i).GetCampID());
        }

        //Returns the index of the camps displayed so if any functions needs access the camp can use
        return indexes;
    }

    public default void DisplayCampDetails(int campID)
    {
        Camp camp = CampController.GetInstance().GetCamps().get(campID);

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
package sc2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * View class for managing the display of information related to CCM (Campus Community Manager).
 * Handles displaying suggestions, enquiries, camp details, and the main menu for CCM.
 * Extends the StudentView class.
 * 
 * @author George Lai
 * @version 1.0
 * @since 25/11/2023
 */
public class CCMView extends StudentView{
    private static CCMView instance = null;
    public CCMView() {
        super();
    }

    /**
     * Gets an instance of the CCMView class.
     * 
     * @return The instance of CCMView.
     */
    public static CCMView GetInstance() {
		if(instance == null)
            instance =  new CCMView();

        return instance;
	}

    /**
     * Displays all suggestions submitted by a CCM.
     * 
     * @param ccm The CCM whose suggestions are to be displayed.
     */
    public void DisplayAllSuggestions(CCM ccm){
        //implement code to print out all the suggestions the Camp Comittee Member has written has written
        System.out.println("Suggestions by Camp Committee Member " + ccm.GetPoints() + ":");
        for (Suggestions suggestion : ccm.GetMySuggestions()) {
            System.out.println("Suggestion: " + suggestion.GetSuggestion());
            System.out.println(" Suggested by: " + ccm.GetName());
            System.out.println(" Status: " + (suggestion.IsStatus() ? "Completed" : "Pending"));
            System.out.println();
        }
    }

    /**
     * Displays all enquiries related to a camp for a user.
     * 
     * @param camp The camp for which enquiries are to be displayed.
     * @param user The user requesting the display.
     */
    public void DisplayAllEnquiries(Camp camp, User user) {
        // Check if the user is a staff or CCM
        if (user instanceof Staff || user instanceof CCM) {
            HashMap<Student, ArrayList<Enquiries>> enquiriesMap = camp.GetEnquiries();
            if (enquiriesMap.isEmpty()) {
                System.out.println("No enquiries found for this camp.");
            } else {
                // display all enquiries under a particular camp
                int count = 1;
                for (Map.Entry<Student, ArrayList<Enquiries>> entry : enquiriesMap.entrySet()) {
                    ArrayList<Enquiries> studentEnquiries = entry.getValue();
                    System.out.println("All Enquiries for Camp " + camp.GetCampName() + ":");
                    for (Enquiries enquiry : studentEnquiries) {
                        System.out.println("\t(" + (count++) +  ") Enquiry: " + enquiry.GetEnquiry() + ", Status: " + enquiry.GetStatus());
                        if(enquiry.GetStatus() == STATUS.CLOSED)
                            System.out.println("\t\tReply: " + enquiry.GetReply());

                    }
                }
            }
        } else {
            System.out.println("You do not have permission to view all enquiries.");
        }
    }

    @Override
    public void DisplayCampDetails(int campID) 
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

        System.out.println("Camp Name: " + camp.GetCampName());
        System.out.println("Date: " + camp.GetDate());
        System.out.println("Registration Dateline: " + camp.GetRegisterCloseDate());
        System.out.println("Camp Group: " + camp.GetUserGrp());
        System.out.println("Location: " + camp.GetLocation());
        System.out.println("Description: " + camp.GetDescription());
        System.out.println("Staff In Charge: " + camp.GetStaffInCharge());

        int slotsLeft = camp.GetTotalSlots() - camp.GetAttendees().size();
        System.out.println("Total Slots Available: " + slotsLeft + "/" + camp.GetTotalSlots());

        if(!camp.GetAttendees().isEmpty())
        {
            System.out.println("Attendees are:");
            int count = 1;
            for(Student s : camp.GetAttendees())
                System.out.println("\t(" + (count++) + ") " + s.GetName());
        }

        slotsLeft = camp.GetCommitteeSlots() - camp.GetCommitteeList().size();
        System.out.println("Committee Member Slots Available: " + slotsLeft + "/" + camp.GetCommitteeSlots());
        if(!camp.GetCommitteeList().isEmpty())
        {
            System.out.println("Attendees are:");
            int count = 1;
            for(CCM c : camp.GetCommitteeList())
                System.out.println("\t(" + (count++) + ") " + c.GetName());
        }

        System.out.println();
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////         Main Loop Stuff         /////////////////////////////////////////////////////////////////////
    @Override
    public void DisplayMainMenu()
    {
        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////        CCM Menu        /////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////////////");

        System.out.println("(0) Log Out");
        System.out.println("Profile");
        System.out.println("\t(1) View Profile");
        System.out.println("Camps");
        System.out.println("\t(2) View / Register / Withdraw");
        System.out.println("Enquiries");
        System.out.println("\t(3) View / Edit / Delete / Submit / Reply");
        System.out.println("Suggestions");
        System.out.println("\t(4) View / Edit / Delete / Submit");
        System.out.println("Other");
        System.out.println("\t(5) Generate List");
        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.print("Enter your Choice: ");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

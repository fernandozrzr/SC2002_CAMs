package sc2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CCMView extends StudentView{
    public CCMView() {
        super();
    }

    public void DisplayAllSuggestions(CCM ccm){
        //implement code to print out all the suggestions the Camp Comittee Member has written has written
        System.out.println("Suggestions by Camp Committee Member " + ccm.GetPoints() + ":");
        for (Suggestions suggestion : ccm.getMySuggestions()) {
            System.out.println("Suggestion: " + suggestion.GetSuggestion());
            System.out.println("Suggested by: " + ccm.getName());
            System.out.println("Status: " + (suggestion.IsStatus() ? "Completed" : "Pending"));
            System.out.println();
        }
    }

    public void DisplayAllEnquiries(Camp camp, User user) {
        // Check if the user is a staff or CCM
        if (user instanceof Staff || user instanceof CCM) {
            HashMap<Student, ArrayList<Enquiries>> enquiriesMap = camp.getEnquiries();
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
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////         Main Loop Stuff         /////////////////////////////////////////////////////////////////////
    @Override
    public void DisplayMainMenu()
    {
        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////        CCM Menu        /////////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////////////");

        System.out.println("Profile");
        System.out.println("\t(1) View Profile");
        System.out.println("Camps");
        System.out.println("\t(2) View / Register / Withdraw");
        System.out.println("Enquiries");
        System.out.println("\t(3) View / Edit / Delete / Submit / Reply");
        System.out.println("Suggestions");
        System.out.println("\t(4) View / Edit / Delete / Submit");

        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.print("Enter your Choice: ");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

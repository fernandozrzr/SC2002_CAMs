package sc2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CCMView{
    public CCMView() {
        super();
    }

    public void DisplayAllSuggestions(int campID, CCM ccm){
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
                for (Map.Entry<Student, ArrayList<Enquiries>> entry : enquiriesMap.entrySet()) {
                    ArrayList<Enquiries> studentEnquiries = entry.getValue();
                    System.out.println("All Enquiries for Camp " + camp.GetCampName() + ":");
                    for (Enquiries enquiry : studentEnquiries) {
                        System.out.println("Enquiry: " + enquiry.GetEnquiry());
                        System.out.println("Asked by: " + enquiry.GetAskBy());
                        System.out.println("Reply: " + enquiry.GetReply());
                        System.out.println();
                    }
                }
            }
        } else {
            System.out.println("You do not have permission to view all enquiries.");
        }
    }
}

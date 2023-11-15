package sc2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CCMView implements SuggestionsView {
    

    public CCMView() {
    }

    public void DisplayMySuggestions(CCM ccm){
        //implement code to print out all the suggestions the Camp Comittee Member has written has written
        System.out.println("Suggestions by Camp Committee Member " + ccm.getPoints() + ":");
        for (Suggestions suggestion : ccm.getMySuggestions()) {
            System.out.println("Suggestion: " + suggestion.getSuggestion());
            System.out.println("Suggested by: " + suggestion.getSuggestedBy());
            System.out.println("Status: " + (suggestion.isStatus() ? "Completed" : "Pending"));
            System.out.println();
        }
    }

    public void DisplayAllEnquiries(Camp camp, User user) {
    // Check if the user is a staff or CCM
        if (user instanceof Staff || user instanceof CCM) {
            HashMap<Student, ArrayList<Enquiries>> enquiriesMap = camp.GetCampEnquiries();
            if (enquiriesMap.isEmpty()) {
                System.out.println("No enquiries found for this camp.");
            } else {
                // display all enquiries under a particular camp
                for (Map.Entry<Student, ArrayList<Enquiries>> entry : enquiriesMap.entrySet()) {
                    ArrayList<Enquiries> studentEnquiries = entry.getValue();
                    System.out.println("All Enquiries for Camp " + camp.GetCampName() + ":");
                    for (Enquiries enquiry : studentEnquiries) {
                        System.out.println("Enquiry: " + enquiry.getEnquiry());
                        System.out.println("Asked by: " + enquiry.getAskBy());
                        System.out.println("Reply: " + enquiry.getReply());
                        System.out.println();
                    }
                }
            }
        } else {
            System.out.println("You do not have permission to view all enquiries.");
        }
    }
}

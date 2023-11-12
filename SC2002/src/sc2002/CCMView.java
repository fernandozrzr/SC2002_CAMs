package sc2002;

import java.util.ArrayList;

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

    public void DisplayAllEnquiries(Camp camp) {
        //display all enquiries under a particular camp
         ArrayList<Enquiries> enquiries = camp.GetEnquires();

        System.out.println("All Enquiries for Camp " + camp.GetCampName() + ":");
        if (enquiries.isEmpty()) {
            System.out.println("No enquiries found for this camp.");
        } else {
            for (Enquiries enquiry : enquiries) {
                System.out.println("Enquiry: " + enquiry.getEnquiry());
                System.out.println("Asked by: " + enquiry.getAskBy());
                System.out.println("Reply: " + enquiry.getReply());
                System.out.println();
            }
        }
    }

}

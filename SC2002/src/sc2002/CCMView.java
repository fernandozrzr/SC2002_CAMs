package sc2002;

import java.util.Dictionary;

public class CCMView implements SuggestionsView {
    private CCMController ccmController;
    private Dictionary<Student, String> enquiriesList;
    private List<Suggestions> suggestionsList;

    public CCMView(CCMController controller) {
        ccmController = controller;
        suggestionsList = ccmController.getCampCommitteeMember().getMySuggestions();
    }

    public void displayAllEnquiries(Camp camp) {
        Dictionary<Student, String> enquiriesList = camp.getEnquiriesList();

        if (enquiriesList.isEmpty()) {
            System.out.println("No enquiries to display for this camp.");
        } else {
            System.out.println("Enquiries for Camp: " + camp.getCampName());
            Enumeration<Student> students = enquiriesList.keys();

            while (students.hasMoreElements()) {
                Student student = students.nextElement();
                String enquiry = enquiriesList.get(student);

                System.out.println("Student: " + student.getName());
                System.out.println("Enquiry: " + enquiry);
                System.out.println("------------------------");
            }
        }
    }

    @Override
    public void displaySuggestions() {
        System.out.println("My Suggestions:");
        for (Suggestions suggestion : suggestionsList) {
            System.out.println("Suggestion: " + suggestion.getSuggestion());
            System.out.println("Status: " + (suggestion.isStatus() ? "Accepted" : "Pending"));
            System.out.println("Suggested By: " + suggestion.getSuggestedBy());
            System.out.println("--------------");
        }
    }
}

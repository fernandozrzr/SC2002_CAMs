package sc2002;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Suggestions {
    private Dictionary<CampCommitteeMember, ArrayList<String>> suggestionsList;

    public Suggestions() {
        suggestionsList = new Hashtable<>();
    }

    public void AddSuggestions(CampCommitteeMember campCommitteeMember, String suggestion) {
        // Get the committee member's existing suggestions or create a new list
        ArrayList<String> committeeSuggestions = suggestionsList.get(campCommitteeMember);
        if (committeeSuggestions == null) {
            committeeSuggestions = new ArrayList<>();
        }
        
        // Add the new suggestion
        committeeSuggestions.add(suggestion);
        
        // Update the suggestions for the committee member
        suggestionsList.put(campCommitteeMember, committeeSuggestions);
    }

    public void DeleteSuggestions(CampCommitteeMember campCommitteeMember, String suggestion) {
        // Get the committee member's suggestions
        ArrayList<String> committeeSuggestions = suggestionsList.get(campCommitteeMember);
        if (committeeSuggestions != null) {
            // Remove the specified suggestion
            committeeSuggestions.remove(suggestion);

            // If the committee member has no more suggestions, remove them from the dictionary
            if (committeeSuggestions.isEmpty()) {
                suggestionsList.remove(campCommitteeMember);
            }
        }
    }

    public void EditSuggestions(CampCommitteeMember campCommitteeMember, String oldSuggestion, String newSuggestion) {
        // Get the committee member's suggestions
        ArrayList<String> committeeSuggestions = suggestionsList.get(campCommitteeMember);
        if (committeeSuggestions != null) {
            // Find and update the specified suggestion
            int index = committeeSuggestions.indexOf(oldSuggestion);
            if (index != -1) {
                committeeSuggestions.set(index, newSuggestion);
            }
        }
    }

    public String viewSuggestions() {
        StringBuilder result = new StringBuilder();
        Enumeration<CampCommitteeMember> committeeMembers = suggestionsList.keys();
        while (committeeMembers.hasMoreElements()) {
            CampCommitteeMember committeeMember = committeeMembers.nextElement();
            result.append("Camp Committee Member: ").append(committeeMember.getName()).append("\n");
            result.append("Suggestions: ").append(suggestionsList.get(committeeMember)).append("\n");
        }
        return result.toString();
    }

    // Method to approve suggestions based on user type
    public String ApproveSuggestions(User user, CampCommitteeMember committeeMember) {
        if (user instanceof Staff) {
            ArrayList<String> committeeSuggestions = suggestionsList.get(committeeMember);
            if (committeeSuggestions != null && !committeeSuggestions.isEmpty()) {
                // Logic to approve suggestion (e.g., process and mark as approved)
                System.out.println("Suggestions approved by Staff");
                return "Approved";
            } else {
                System.out.println("There are no suggestions to approve for this committee member.");
                return "No Suggestions";
            }
        } else {
            System.out.println("You do not have the authorization to approve a suggestion.");
            return "Pending Approval";
        }
    }
}

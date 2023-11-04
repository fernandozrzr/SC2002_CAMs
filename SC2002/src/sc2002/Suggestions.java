import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Suggestions {
    private Map<CampCommitteeMember, List<String>> suggestionsList;

    public Suggestions() {
        suggestionsList = new HashMap<>();
    }

    public void addSuggestions(CampCommitteeMember campCommitteeMember, String suggestion) {
        List<String> committeeSuggestions = suggestionsList.get(campCommitteeMember);
        if (committeeSuggestions == null) {
            committeeSuggestions = new ArrayList<>();
            suggestionsList.put(campCommitteeMember, committeeSuggestions);
        }
        committeeSuggestions.add(suggestion);
    }
    

    public void deleteSuggestions(CampCommitteeMember campCommitteeMember, String suggestion) {
        List<String> committeeSuggestions = suggestionsList.get(campCommitteeMember);
        if (committeeSuggestions != null) {
            committeeSuggestions.remove(suggestion);
            if (committeeSuggestions.isEmpty()) {
                suggestionsList.remove(campCommitteeMember);
            }
        }
    }

    public void editSuggestions(CampCommitteeMember campCommitteeMember, String oldSuggestion, String newSuggestion) {
        List<String> committeeSuggestions = suggestionsList.get(campCommitteeMember);
        if (committeeSuggestions != null) {
            int index = committeeSuggestions.indexOf(oldSuggestion);
            if (index != -1) {
                committeeSuggestions.set(index, newSuggestion);
            }
        }
    }

    public String viewSuggestions() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<CampCommitteeMember, List<String>> entry : suggestionsList.entrySet()) {
            CampCommitteeMember committeeMember = entry.getKey();
            result.append("Camp Committee Member: ").append(committeeMember.getName()).append("\n");
            result.append("Suggestions: ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    public String approveSuggestions(User user, CampCommitteeMember committeeMember) {
        if (user instanceof Staff) {
            List<String> committeeSuggestions = suggestionsList.get(committeeMember);
            if (committeeSuggestions != null && !committeeSuggestions.isEmpty()) {
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

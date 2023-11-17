// CCMController.java
package sc2002;

import java.util.ArrayList;

public class CCMController {
    private static CCMController instance = null;
    private int ids = 0;
    private ArrayList<CCM> ccm;

    public static CCMController GetInstance() {
        if (instance == null)
            instance = new CCMController();
        return instance;
    }

    private CCMController() {
        ccm = new ArrayList<CCM>();
    }

    public ArrayList<CCM> getCCM() {
        return ccm;
    }

    public void DeleteSuggestion(int campID, CCM ccm, int suggestionID) {
        // Delete a suggestion from the camp's suggestions list
        Suggestions s = ccm.FindSuggestion(suggestionID);
        ccm.DeleteMySuggestion(s);
        CampController.GetInstance().RemoveSuggestion(campID, ccm, s);
    }

    public void AddSuggestion(int campID, CCM ccm, String suggestion) {
        // Add a suggestion to the camp's suggestions list
        Suggestions _suggestion = new Suggestions(ids++, suggestion, false);
        ccm.AddMySuggestion(_suggestion);
        CampController.GetInstance().AddSuggestion(campID, ccm, _suggestion);
    }

    public void EditSuggestion(int campID, CCM ccm, int suggestionID, String edited) {
        // Add a suggestion to the camp's suggestions list
        Suggestions s = ccm.FindSuggestion(suggestionID);
        s.SetSuggestion(edited);
    }

    public void ReplyEnquiry(int campID, Student student, String enquiry, String reply, String replyBy) {
        // Add a reply to a particular enquiry, and leave the replyBy person, and increment the point of the replyBy ccm
        // CampController here, may nid to edit the line below
        CampController.GetInstance().ReplyEnquiry(campID, student, enquiry, reply, replyBy);
    }

    public int GetPoints(CCM ccm) {
        // return the number of points the CampComitteeMember has
        return ccm.GetPoints();
    }

    public String GenerateList(Camp camp, String role, String format) {
        // return a generated list in txt
        return "list";
    }


}

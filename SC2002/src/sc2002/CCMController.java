// CCMController.java
package sc2002;

import java.util.ArrayList;

public class CCMController {
    private static CCMController instance = null;
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

    public void DeleteSuggestion(Camp camp, CCM ccm, Suggestions suggestion) {
        // Delete a suggestion from the camp's suggestions list
    }

    public void AddSuggestion(Camp camp, Suggestions suggestion) {
        // Add a suggestion to the camp's suggestions list
    }

    public void EditSuggestion(Camp camp, Suggestions suggestion) {
        // Add a suggestion to the camp's suggestions list
    }

    public void ReplyEnquiry(Camp camp, CCM ccm, String enquiry, String reply) {
        // Add a reply to a particular enquiry, and leave the replyBy person, and increment the point of the replyBy ccm
        camp.ReplyEnquiry(ccm, enquiry, reply);
    }

    public int GetPoints(CCM ccm) {
        // return the number of points the CampComitteeMember has
        return ccm.getPoints();
    }

    public String GenerateList(Camp camp, String role, String format) {
        // return a generated list in excel
        return "list";
    }


}

// CCMController.java
package sc2002;

import java.io.FileWriter;
import java.io.IOException;
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
        // Increment points for the CCM
        ccm.SetPoints(ccm.GetPoints()+1);
    }

    public void EditSuggestion(int campID, CCM ccm, int suggestionID, String edited) {
        // Edit a suggestion in the camp's suggestions list
        Suggestions s = ccm.FindSuggestion(suggestionID);
        s.SetSuggestion(edited);
    }

    public void ReplyEnquiry(int campID, CCM ccm, Student student, String enquiry, String reply, String replyBy) {
        // Add a reply to a particular enquiry, and leave the replyBy person, and increment the point of the replyBy ccm
        Camp camp = CampController.GetInstance().GetCamps().get(campID);    
        Enquiries e = camp.GetEnquiry(student, enquiry);

        if (e != null && e.getStatus().equals("Open")) {
            e.setReply(reply);
            e.setStatus("Replied");
            e.setReplyBy(replyBy);

            // Increment points for the CCM
            ccm.SetPoints(ccm.GetPoints()+1);
        }
        CampController.GetInstance().ReplyEnquiry(campID, student, enquiry, reply, replyBy);
    }

    public int GetPoints(CCM ccm) {
        // return the number of points the CampComitteeMember has
        return ccm.GetPoints();
    }

    public String GenerateList(int campID, String roleFilter) {
        Camp camp = CampController.GetInstance().GetCamps().get(campID);

        if (camp != null) {
            ArrayList<Student> attendees = camp.GetAttendees();
            ArrayList<CCM> committeeMembers = camp.GetCommitteeMembers();
    

            StringBuilder report = new StringBuilder();
            report.append("Camp Details:\n");
            report.append("Camp Name: ").append(camp.GetCampName()).append("\n");
            report.append("Date: ").append(camp.GetDate()).append("\n");
            report.append("Location: ").append(camp.GetLocation()).append("\n");
            report.append("Staff in Charge: ").append(camp.GetStaffInCharge()).append("\n");

            // Apply filters and generate participant list
            report.append("\nParticipants List:\n");
            for (Student attendee : attendees) {
                if (roleFilter.equals("attendee")) {
                    report.append("Attendee: ").append(attendee.getName()).append("\n");
                }
            }
            for (CCM ccm : committeeMembers) {
                if (roleFilter.equals("ccm")) {
                    report.append("CCM: ").append(ccm.getName()).append("\n");
                }
            }
            if (roleFilter.equals("staff")) {
                report.append("Staff: ").append(camp.GetStaffInCharge()).append("\n");
            }

            // Write the report to a txt file
            String fileName = camp.GetCampName() + "_Participant_List.txt";
            WriteToFile(fileName, report.toString());

            return fileName;
        } else {
            return "Camp not found.";
        }
    }

    private void WriteToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Report generated successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


}
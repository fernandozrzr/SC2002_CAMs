package sc2002;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CampController 
{
    private static CampController instance = null;

    private ArrayList<Camp> camps;

    public static CampController GetInstance()
    {
        if(instance == null)
            instance =  new CampController();

        return instance;
    }

    private CampController()
    {
        camps = new ArrayList<Camp>();
    }

    public ArrayList<Camp> GetCamps()
    {
        return camps;
    }

    public boolean AddAttendee(int campID, Student student)
    {
        return camps.get(campID).AddAttendee(student);
    }

    public boolean RemoveAttendee(int campID, Student student)
    {
        return camps.get(campID).RemoveAttendee(student);
    }

    public boolean AddCommitteeMember(int campID, CCM ccm)
    {
        return camps.get(campID).AddCommitteeMember(ccm);
    }

    public void ToggleVisibility(int campID, boolean state)
    {
        camps.get(campID).SetVisibility(state);
    }

    public void DeleteCamp(int campID) {
        if (campID >= 0 && campID < camps.size()) {
            camps.get(campID).DeleteCamp();
            camps.set(campID, null);

            // Rearrange the remaining camps to push them to the front
            int fillIndex = 0;
            for (int i = 0; i < camps.size(); i++) {
                if (camps.get(i) != null) {
                    camps.set(fillIndex, camps.get(i));
                    camps.get(i).SetCampID(fillIndex);
                    fillIndex++;
                }
            }

            // Fill the rest of the array with nulls
            while (fillIndex < camps.size()) {
                camps.set(fillIndex++, null);
            }
            SortCampsByName(camps);
            ReassignCampIDs(camps);
        } else {
            throw new IndexOutOfBoundsException("Index " + campID + " is out of bounds.");
        }
    }

    public Camp AddCamp(String campName, String date, String registerCloseDate, String userGrp, String location, 
                            String desc, String staffInCharge, int totalSlots, int committeeSlots, boolean visibility)
    {
        //Determine campID
        int campID = camps.indexOf(null); // Get Index of the first empty space in array
        if(campID == -1) //No Empty space in array 
            campID = camps.size();

        Camp camp = new Camp(campID, campName, date, registerCloseDate, userGrp, 
                location, desc, staffInCharge, totalSlots, committeeSlots, visibility);

        if(campID == camps.size())
            camps.add(camp);
        else
            camps.set(campID, camp);
        SortCampsByName(camps);
        ReassignCampIDs(camps);
        return camp; //Added for for StaffController
    }

    public boolean AddSuggestion(int campID, CCM ccm, Suggestions suggestion)
    {
        camps.get(campID).AddSuggestion(ccm, suggestion);
        return true;
    }

    public boolean RemoveSuggestion(int campID, CCM ccm, Suggestions suggestion)
    {
        return camps.get(campID).RemoveSuggestion(ccm, suggestion);
    }

    public boolean AddEnquiry(int campID, Student student, Enquiries enqury)
    {
        camps.get(campID).AddEnquiry(student, enqury);
        return true;
    }

    public boolean RemoveEnquiry(int campID, Student student, Enquiries enqury)
    {
        return camps.get(campID).RemoveEnquiry(student, enqury);
    }

    public void ReplyEnquiry(int campID, Student student, String enquiry, String reply, String replyBy)
    {
        Enquiries e = camps.get(campID).GetEnquiry(student, enquiry);
        e.SetReply(reply);
        e.SetReplyBy(replyBy);
        e.SetStatus(STATUS.CLOSED);
    }

    public Boolean AlreadyRegistered(int campID, String name)
    {
        ArrayList<Student> attendees = camps.get(campID).GetAttendees();

        for(Student s : attendees)
            if(s.name.equals(name)) return true;

        return false;
    }

    public Boolean AlreadyCommittee(int campID, String name)
    {
        ArrayList<CCM> attendees = camps.get(campID).GetCommitteeList();

        for(CCM c : attendees)
            if(c.name.equals(name)) return true;

        return false;
    }

    // Sort camp by alphabetical order
    public void SortCampsByName(ArrayList<Camp> camps) {
        Collections.sort(camps, new Comparator<Camp>() {
            @Override
            public int compare(Camp camp1, Camp camp2) {
                if (camp1 == null && camp2 == null) {
                    return 0;
                } else if (camp1 == null) {
                    return 1; // null is considered greater (placed at the end)
                } else if (camp2 == null) {
                    return -1; // null is considered greater (placed at the end)
                } else {
                    // Compare camp names in alphabetical order
                    return camp1.GetCampName().compareTo(camp2.GetCampName());
                }
            }
        });
    }
    //Reassign CampID based on current order of array
    public void ReassignCampIDs(ArrayList<Camp> camps) {
        int id = 0;
        for (Camp camp : camps) {
            if (camp != null) {
                camp.SetCampID(id++);
            }
        }
    }
        
}
    

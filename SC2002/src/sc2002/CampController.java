package sc2002;

import java.util.ArrayList;

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

    public boolean DeleteCamp(int campID)
    {
        if(campID >= 0 && campID < camps.size())
        {
            camps.set(campID, null);
            return true;
        }
        else
            throw new IndexOutOfBoundsException("Index" + campID + "is out of bounds.");
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
}

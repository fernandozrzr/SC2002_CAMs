package sc2002;

import java.util.ArrayList;

public class Camp 
{
    private String campName;
    private String date;
    private String registerCloseDate;
    private String userGrp;
    private String location;
    private String description;
    private String staffInCharge;
    
    private int totalSlots;
    private int committeeSlots;

    private boolean visibility;

    private ArrayList<Student> attendees;
    private ArrayList<Student> studentRemoved;
    private ArrayList<CCM> committeeMembers;
    private ArrayList<Enquiries> enquiries;
    private ArrayList<Suggestions> suggestions;

    public Camp(String campName, String date, String registerCloseDate, String userGrp, String location, 
                    String desc, String staffInCharge, int totalSlots, int committeeSlots, boolean visibility)
    {
        this.campName = campName;
        this.date = date;
        this.registerCloseDate = registerCloseDate;
        this.userGrp = userGrp;
        this.location = location;
        this.description = desc;
        this.staffInCharge = staffInCharge;
        this.totalSlots = totalSlots;
        this.committeeSlots = committeeSlots;
        this.visibility = visibility;

        attendees = new ArrayList<Student>(totalSlots);
        studentRemoved = new ArrayList<Student>(totalSlots);
        committeeMembers = new ArrayList<CCM>(committeeSlots);
        enquiries = new ArrayList<Enquiries>();
        suggestions = new ArrayList<Suggestions>();
    }

    public String GetCampName() 
    {
        return campName;
    }

    public void SetCampName(String campName) 
    {
        this.campName = campName;
    }

    public String GetDate() 
    {
        return date;
    }

    public void SetDate(String date) 
    {
        this.date = date;
    }

    public String GetRegisterCloseDate() 
    {
        return registerCloseDate;
    }

    public void SetRegisterCloseDate(String registerCloseDate) 
    {
        this.registerCloseDate = registerCloseDate;
    }

    public String GetUserGrp() {
        return userGrp;
    }

    public void SetUserGrp(String userGrp) 
    {
        this.userGrp = userGrp;
    }

    public String GetLocation() 
    {
        return location;
    }

    public void SetLocation(String location) 
    {
        this.location = location;
    }

    public String GetDescription() 
    {
        return description;
    }

    public void SetDescription(String description) 
    {
        this.description = description;
    }

    public String GetStaffInCharge() 
    {
        return staffInCharge;
    }

    public void SetStaffInCharge(String staffInCharge) 
    {
        this.staffInCharge = staffInCharge;
    }

    public int GetTotalSlots() 
    {
        return totalSlots;
    }

    public void SetTotalSlots(int totalSlots) 
    {
        this.totalSlots = totalSlots;
    }

    public int GetCommitteeSlots() 
    {
        return committeeSlots;
    }

    public void SetCommitteeSlots(int committeeSlots) 
    {
        this.committeeSlots = committeeSlots;
    }

    public boolean IsVisible() 
    {
        return visibility;
    }

    public void SetVisibility(boolean visibility) 
    {
        this.visibility = visibility;
    }

    public ArrayList<Student> GetAttendees() 
    {
        return attendees;
    }

    public boolean AddAttendee(Student student)
    {
        //There are slots left to join and student is not removed
        if(attendees.size() < totalSlots && !studentRemoved.contains(student))
        {
            attendees.add(student);
            return true;
        }

        return false;
    }

    public boolean RemoveAttendee(Student student)
    {
        if(attendees.contains(student))
        {
            attendees.remove(student);
            studentRemoved.add(student);
            return true;
        }

        return false;
    }

    public ArrayList<Student> GetStudentRemoved() 
    {
        return studentRemoved;
    }

    public ArrayList<CCM> GetCommitteeMembers() 
    {
        return committeeMembers;
    }

    public boolean AddCommitteeMember(CCM ccm)
    {
        if(committeeMembers.size() < committeeSlots)
        {
            committeeMembers.add(ccm);
            return true;
        }

        return false;
    }

    public ArrayList<Enquiries> GetEnquires() 
    {
        return enquiries;
    }

    public ArrayList<Suggestions> GetSuggestions() 
    {
        return suggestions;
    }

    public void AddSuggestions(Suggestions suggestion) {
        suggestions.add(suggestion);
    }

    public void DeleteSuggestions(CCM ccm, Suggestions suggestion) {
        Suggestions suggestionToRemove = null;
        for (Suggestions s : suggestions) {
            if (s.getSuggestion().equals(suggestion) && s.getSuggestedBy().equals(ccm)) {
                suggestionToRemove = s;
                break;
            }
        }
        if (suggestionToRemove != null) {
            suggestions.remove(suggestionToRemove);
        }
    }

    public void ReplyEnquiries(CCM ccm, String enquiry, String reply) {
        Enquiries enquiryToReply = null;
        for (Enquiries e : enquiries) {
            if (e.getEnquiry().equals(enquiry)) {
                enquiryToReply = e;
                break;
            }
        }
        if (enquiryToReply != null) {
            enquiryToReply.setReply(reply);
            //enquiryToReply.setReplyBy(ccm); How to convert CCM data type to string?

            // Increment the points of the Enquiries
            ccm.setPoints(ccm.getPoints() + 1);
        }
    }
}

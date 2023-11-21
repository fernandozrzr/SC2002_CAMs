package sc2002;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Camp 
{
    private int campID;

    private String campName;
    private String date;
    private String registerCloseDate; // In Format of yyyy/mm/dd
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
    private HashMap<Student, ArrayList<Enquiries>> enquiries;
    private HashMap<CCM, ArrayList<Suggestions>> suggestions;

    public Camp(int campID, String campName, String date, String registerCloseDate, String userGrp, String location, 
                    String desc, String staffInCharge, int totalSlots, int committeeSlots, boolean visibility)
    {
        this.campID = campID;
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
        enquiries = new HashMap<Student, ArrayList<Enquiries>>();
        suggestions = new HashMap<CCM, ArrayList<Suggestions>>();
    }

    public int GetCampID()
    {
        return campID;
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
    public ArrayList<CCM> GetCommitteeList() 
    {
        return committeeMembers;
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

    public boolean AddAttendee(Student student) //TODO CHECK WHETHER REGISTERATION DATE HAS PASSED
    {
        //There are slots left to join and student is not removed
        if(attendees.size() < totalSlots && !studentRemoved.contains(student))
        {
            attendees.add(student);
            System.out.println("You have successfully registered for " + campName + " as an attendee");
            return true;
        }

        System.out.println("The Camp is either fully registered, the registeration date has passed or " + 
            "you have previously withdrawn from this camp.");
        return false;
    }

    public boolean RemoveAttendee(Student student)
    {
        if(attendees.contains(student))//TODO CHECK IF STUDENT IS A CCM OF THIS CAMP
        {
            attendees.remove(student);
            studentRemoved.add(student);
            System.out.println("You have successfully withdrawn from " + campName);

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

    public boolean AddCommitteeMember(CCM ccm)//TODO CHECK IF CCM IS AREADY A CCM IN ANOTHER CAMP
    {
        if(committeeMembers.size() < committeeSlots)
        {
            committeeMembers.add(ccm);
            System.out.println("You have successfully registered for " + campName + " as a Committee Member");

            return true;
        }
        System.out.println("The Camp is either fully registered, the registeration date has passed or " + 
            "you are already a committee member of another camp.");
        return false;
    }

    public void AddSuggestion(CCM committee, Suggestions suggestion)
    {
        if(!suggestions.containsKey(committee))
            suggestions.put(committee, new ArrayList<Suggestions>());
        
        suggestions.get(committee).add(suggestion);
    }
    

    public boolean RemoveSuggestion(CCM committee, Suggestions suggestion)
    {
        //Check if the suggestions list have any sugguestion from this member
        if(suggestions.containsKey(committee))
        {
            //Check if the sugguestions has been suggested by this member
            if(suggestions.get(committee).contains(suggestion))
            {
                suggestions.get(committee).remove(suggestion);
                return true;
            }

            return false;
        }

        return false;
    }

    public void AddEnquiry(Student student, Enquiries enquiry)
    {
        if(!enquiries.containsKey(student))
            enquiries.put(student, new ArrayList<Enquiries>());
        
        enquiries.get(student).add(enquiry);
    }

    public boolean RemoveEnquiry(Student student, Enquiries enquiry)
    {
        //Check if the Enquiries list have any enquiry from this student
        if(enquiries.containsKey(student))
        {
            //Check if the Enquiries has been enquired by this student
            if(enquiries.get(student).contains(enquiry))
            {
                enquiries.get(student).remove(enquiry);
                return true;
            }

            return false;
        }

        return false;
    }

    public Enquiries GetEnquiry(Student student, String enquiry)
    {
        //Check if the Enquiries list have any enquiry from this student
        if(enquiries.containsKey(student))
        {
            for(Enquiries e : enquiries.get(student))
                if(e.GetEnquiry() == enquiry) return e;
        }

        return null;
    }
    public HashMap<Student, ArrayList<Enquiries>> getEnquiries() {
        return enquiries;
    }
    public HashMap<CCM, ArrayList<Suggestions>> getSuggestion()
    {
    	return suggestions;
    }

    public boolean CanRegister()
    {
        Date tdy = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date rD = null;
        try {
            rD = sdf.parse(registerCloseDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return tdy.before(rD);
    }

}

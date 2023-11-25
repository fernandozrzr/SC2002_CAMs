package sc2002;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Camp Data Structure (Model)
 * 
 * @author Edward Seah
 * @version 1.0
 * @since 24/11/2023
 */
public class Camp 
{
    /**
	 * ID of the camp used to identify camps.
	 */
    private int campID;
    /**
	 * Name of the camp
	 */
    private String campName;
    /**
	 * The date that the camp is taking place
     * The format of the date is in dd/MM/yyy
	 */
    private String date;
    /**
	 * The registration closing date
     * The format of the date is in dd/MM/yyy
	 */
    private String registerCloseDate;
    /**
	 * The faculty that this camp belongs to
     * Only students in this faculty can view and join this camp
	 */
    private String userGrp;
    /**
	 * Location of where the camp is held
	 */
    private String location;
    /**
	 * Description of the camp
	 */
    private String description;
    /**
	 * Name of the staff that created the camp 
	 */
    private String staffInCharge;
    
    /**
	 * Number of total slots available for students to join
     * This number is inclusive of the number of committee member this camp has
	 */
    private int totalSlots;
    /**
	 * Number of committee member slots available for stuednts to join
     * Max no. is 10
	 */
    private int committeeSlots;

    /**
	 * Determines whether this camp is visible for students of the same faculty to join
	 */
    private boolean visibility;

    /**
	 * Stores the list of attendees who are not committee member
	 */
    private ArrayList<Student> attendees;
    /**
	 * Stores the list of students that has withdrawn from this camp
     * Once they withdraw they are not able to join back the camp
	 */
    private ArrayList<Student> studentRemoved;
    /**
	 * Stores the list of committee Members of this camp
	 */
    private ArrayList<CCM> committeeMembers;
    /**
	 * A map of enquiries
     * Key: Student
     * Value: List of enquiries about this camp submitted by the student(key)
	 */
    private HashMap<Student, ArrayList<Enquiries>> enquiries;
    /**
	 * A map of suggestions
     * Key: CCM
     * Value: List of suggestions of this camp submitted by the committee member(key)
	 */
    private HashMap<CCM, ArrayList<Suggestions>> suggestions;


    /**
	 * Constructor of Camp object
     * Initialises the various variables attached to this camp
	 * 
	 * @param campID ID of the camp
     * @param campName Name of the camp
     * @param date Date that the camp is held
     * @param registerCloseDate Registration Closing Date
     * @param userGrp Faculty of this camp
     * @param location Where the camp is held
     * @param desc Description of the camp 
     * @param staffInCharge Name of staff that created the camp
     * @param totalSlots  Number of slots available for students to join
     * @param committeeSlots Number of committee member slots available for students to join (Max 10)
     * @param visibility Whether the camp can be seen by students
	 */
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

    /**
	 * Gets the campID
	 *  
	 * @return This camp's ID
	 */
    public int GetCampID()
    {
        return campID;
    }
    
    /**
	 * Sets the camp id of the camp
	 * 
	 * @param campID The campID of this camp
	 */
    public void SetCampID(int campID)
    {
        this.campID = campID ;
    }

    /**
	 * Gets the camp's name
	 *  
	 * @return This camp's name
	 */
    public String GetCampName() 
    {
        return campName;
    }

    /**
	 * Sets the name of the camp
	 * 
	 * @param campName This camp's name
	 */
    public void SetCampName(String campName) 
    {
        this.campName = campName;
    }

    /**
	 * Gets the date that this camp is held
	 *  
	 * @return Date of when this camp is held
	 */
    public String GetDate() 
    {
        return date;
    }

    /**
	 * Sets the date that this camp is held
	 * 
	 * @param date Date of when this camp is held
	 */
    public void SetDate(String date) 
    {
        this.date = date;
    }

    /**
	 * Gets the registration closing date of the camp
	 *  
	 * @return Registration closing date of this camp
	 */
    public String GetRegisterCloseDate() 
    {
        return registerCloseDate;
    }

    /**
	 * Sets registration closing date of the camp
	 * 
	 * @param registerCloseDate The registration closing date of this camp
	 */
    public void SetRegisterCloseDate(String registerCloseDate) 
    {
        this.registerCloseDate = registerCloseDate;
    }

    /**
	 *  Gets the faculty that the camp belongs to
	 *  
	 * @return Faculty of this camp
	 */
    public String GetUserGrp() 
    {
        return userGrp;
    }

    /**
	 * Sets faculty that the camp belongs to
	 * 
	 * @param userGrp Faculty of this camp
	 */
    public void SetUserGrp(String userGrp) 
    {
        this.userGrp = userGrp;
    }

    /**
	 * Gets the location the camp is held at
	 *  
	 * @return Location of where this camp is held
	 */
    public String GetLocation() 
    {
        return location;
    }

    /**
	 * Sets location where the camp is held at
	 * 
	 * @param location Location of where this camp is held
	 */
    public void SetLocation(String location) 
    {
        this.location = location;
    }

    /**
	 * Gets the description of the camp
	 *  
	 * @return Description of this camp
	 */
    public String GetDescription() 
    {
        return description;
    }

    /**
	 * Sets description of the camp
	 * 
	 * @param description Description of the this camp
	 */
    public void SetDescription(String description) 
    {
        this.description = description;
    }

    /**
	 * Gets which staff created the camp
	 *  
	 * @return Staff that created this camp
	 */
    public String GetStaffInCharge() 
    {
        return staffInCharge;
    }

    /**
	 * Sets which staff created the camp
	 * 
	 * @param staffInCharge Staff that created this camp
	 */
    public void SetStaffInCharge(String staffInCharge) 
    {
        this.staffInCharge = staffInCharge;
    }

    /**
	 * Gets number of total slots of the camp
	 *  
	 * @return Total slot number of this camp
	 */
    public int GetTotalSlots() 
    {
        return totalSlots;
    }

    /**
	 * Sets total slots of the camp
	 * 
	 * @param totalSlots Total slot number of this camp
	 */
    public void SetTotalSlots(int totalSlots) 
    {
        this.totalSlots = totalSlots;
    }
    
    /**
	 * Gets number of committee member slots of the camp
	 *  
	 * @return Number of committee member slots of this camp
	 */
    public int GetCommitteeSlots() 
    {
        return committeeSlots;
    }

    /**
	 * Gets list of committee member of camp
	 *  
	 * @return List of committee member of this camp
	 */
    public ArrayList<CCM> GetCommitteeList() 
    {
        return committeeMembers;
    }

    /**
	 * Sets number of committe member slots available in the camp
	 * 
	 * @param committeeSlots Number of committee member slots available in this camp
	 */
    public void SetCommitteeSlots(int committeeSlots) 
    {
        this.committeeSlots = committeeSlots;
    }

    /**
	 * Gets the visibility of this camp
	 *  
	 * @return Visiblity of this camp
	 */
    public boolean IsVisible() 
    {
        return visibility;
    }

    /**
	 * Sets visibility of the camp
	 * 
	 * @param visiblity Visiblity of this camp
	 */
    public void SetVisibility(boolean visibility) 
    {
        this.visibility = visibility;
    }

    /**
	 * Gets the lis of student who are attending the camp
	 *  
	 * @return List of students attending this camp 
	 */
    public ArrayList<Student> GetAttendees() 
    {
        return attendees;
    }

    /**
	 * Adds student to the list of attendees
	 * 
	 * @param student Student that wants to join the camp
	 * @return True if student is successfully registered in the camp, False if unable to register for the camp
	 */
    public boolean AddAttendee(Student student)
    {
        //There are slots left to join and student is not removed
        if(attendees.size() < (totalSlots - committeeMembers.size()) && !studentRemoved.contains(student))
        {
            attendees.add(student);
            System.out.println("You have successfully registered for " + campName + " as an attendee");
            return true;
        }

        System.out.println("The Camp is either fully registered, the registration date has passed or " + 
            "you have previously withdrawn from this camp.");
        return false;
    }

    /**
	 * Removes student from the list of attendees and adds them to the list of students that have withdrawn from the camp
	 * 
	 * @param student The Student that wants to withdraw from the camp
	 * @return True if student have successfully withdrawn, False if student is unable to withdraw
	 */
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

    /**
	 * Gets the list of students that have withdrawn from the camp
	 *  
	 * @return List of student that withdrawn from this camp
	 */
    public ArrayList<Student> GetStudentRemoved() 
    {
        return studentRemoved;
    }

    /**
	 * Adds student to the committee member list 
	 * 
	 * @param ccm student(of type CCM) that wants to register as a committee memember
	 * @return True if student have successfully registered, Falise if they failed to register
	 */
    public boolean AddCommitteeMember(CCM ccm)
    {
        if(committeeMembers.size() < committeeSlots)
        {
            committeeMembers.add(ccm);
            System.out.println("You have successfully registered for " + campName + " as a Committee Member");

            return true;
        }
        System.out.println("The Camp is either fully registered, the registration date has passed or " + 
            "you are already a committee member of another camp.");
        return false;
    }

    /**
	 * Adds the suggestion from a committee member to the list of suggestions by that member
	 * 
	 * @param committee The Committee member that suggested the suggestion
     * @param suggestion The suggestion made by the member
	 */
    public void AddSuggestion(CCM committee, Suggestions suggestion)
    {
        if(!suggestions.containsKey(committee))
            suggestions.put(committee, new ArrayList<Suggestions>());
        
        suggestions.get(committee).add(suggestion);
    }
    
    /**
	 * Removes suggestion from camp's list of suggestions by the committee member
	 * 
	 * @param committee The Committee mmeber that suggested the suggestion
     * @param suggestion The suggestion to be deleted
	 * @return True if suggestion have been deleted, False if not deleted
	 */
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

    /**
	 * Adds enquiry made by the student to the camp's list of enquiries by that student
	 * 
	 * @param student The student that submitted the enquiry
     * @param enquiry The enquiry that the student submitted
	 */
    public void AddEnquiry(Student student, Enquiries enquiry)
    {
        if(!enquiries.containsKey(student))
            enquiries.put(student, new ArrayList<Enquiries>());
        
        enquiries.get(student).add(enquiry);
    }

    /**
	 * Removes the enquiry from the camp's list of enquiries by that student
	 * 
	 * @param student The student that submitted the enquiry
     * @param enquiry The enquiry to be deleted
	 * @return True if enquiry have been deleted, False if not deleted
	 */
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

    /**
	 * Gets a particular enquiry submitted by student
	 * 
	 * @param student The student that submitted the enquiry
     * @param enquiry The question that the student asked
	 * @return Enquiries object with infomation on who asked, status, the query, replies
	 */
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

    /**
	 * Gets map of students to their list of enquiries about the camp
	 *  
	 * @return Map of student to their list of enquiries about this camp
	 */
    public HashMap<Student, ArrayList<Enquiries>> GetEnquiries() 
    {
        return enquiries;
    }

    /**
	 * Gets map of committee member to their list of suggestion of the camp
	 *  
	 * @return Map of committee member to their list of suggestion of this camp
	 */
    public HashMap<CCM, ArrayList<Suggestions>> GetSuggestion()
    {
    	return suggestions;
    }

    /**
	 * Checks if the registration date has passed
	 * 
	 * @return True if students are still allowed to register, False otherwise
	 */
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

    /**
	 * Handles the deletion of this camp
     * i.e removes this camp from student's list of registered camps,
     * if a student was a committee member of this camp, remove their ccm status
	 * 
	 */
    public void DeleteCamp()
    {
        for(Student s : attendees)
            s.RemoveRegisteredCamps(this);

        for(CCM c : committeeMembers)
        {
            Student s = new Student(c.GetName(), c.GetUserID(), c.GetFaculty(), c.GetMyEnquiries(), c.GetRegisteredCamps());

            Auth.UpdateAcccounts(s);
        }
    }
}

package sc2002;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Controller class which controls all the logic related to camps
 * 
 * @author Edward Seah
 * @version 1.0
 * @since 24/11/2023
 */
public class CampController 
{
    /**
	 * Static instance of CampController
	 */
    private static CampController instance = null;

    /**
	 * List of camps created
	 */
    private ArrayList<Camp> camps;

    /**
	 * If instance is null, create a new instance of CampController
     * else return the instance
	 * 
	 * @return CampController instance
	 */
    public static CampController GetInstance()
    {
        if(instance == null)
            instance =  new CampController();

        return instance;
    }

    /**
	 * Default Constructor of CampController
     * Initialises list of camps
	 * 
	 */
    private CampController()
    {
        camps = new ArrayList<Camp>();
    }

    /**
	 * Gets the list of camps created
	 * 
	 * @return List of camps created
	 */
    public ArrayList<Camp> GetCamps()
    {
        return camps;
    }

    /**
	 * Adds the student to a camp's list of attendees
	 * 
     * @param campID ID of the camp that student wants to register for
     * @param student Student that wants to register
	 * @return True if successfully registered, False otherwise
	 */
    public boolean AddAttendee(int campID, Student student)
    {
        return camps.get(campID).AddAttendee(student);
    }

    /**
	 * Removes the student from a camp's list of attendees
	 * 
     * @param campID ID of the camp that student wants withdraw from
     * @param student Student that wants to withdraw
	 * @return True if successfully withdrawn, False otherwise
	 */
    public boolean RemoveAttendee(int campID, Student student)
    {
        return camps.get(campID).RemoveAttendee(student);
    }

    /**
	 * Adds the student from a camp's list of committee members
	 * 
     * @param campID ID of the camp that student wants join as CCM
     * @param ccm Student that wants to register as CCM
	 * @return True if successfully registered, False otherwise
	 */
    public boolean AddCommitteeMember(int campID, CCM ccm)
    {
        return camps.get(campID).AddCommitteeMember(ccm);
    }

    /**
	 * Sets the vibility of a camp
	 * 
     * @param campID ID of the camp
     * @param state visibilty states of the camp
	 */
    public void ToggleVisibility(int campID, boolean state)
    {
        camps.get(campID).SetVisibility(state);
    }

    /**
	 * Remove camp from the created camps and updates their respective IDs
	 * 
     * @param campID ID of the camp to delete
	 */
    public void DeleteCamp(int campID) 
    {
        if (campID >= 0 && campID < camps.size()) 
        {
            camps.get(campID).DeleteCamp();
            camps.set(campID, null);

            // Rearrange the remaining camps to push them to the front
            int fillIndex = 0;
            for (int i = 0; i < camps.size(); i++) 
            {
                if (camps.get(i) != null) 
                {
                    camps.set(fillIndex, camps.get(i));
                    camps.get(i).SetCampID(fillIndex);
                    fillIndex++;
                }
            }

            // Fill the rest of the array with nulls
            while (fillIndex < camps.size()) 
                camps.set(fillIndex++, null);

            SortCampsByName(camps);
            ReassignCampIDs(camps);
        } 
        else 
            throw new IndexOutOfBoundsException("Index " + campID + " is out of bounds.");
    }

    /**
	 * Adds created camp into the list of created camp
	 * 
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
     * @return Camp object created using the parameters
	 */
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
        return camp;
    }

    /**
	 * Add suggestion submitted by committee member to a camp's suggestions list
	 * 
     * @param campID ID of the camp
     * @param ccm Committee Member that submitted the suggestion
     * @param suggestion Suggestion submitted 
     * @return True once suggestion is added
	 */
    public boolean AddSuggestion(int campID, CCM ccm, Suggestions suggestion)
    {
        camps.get(campID).AddSuggestion(ccm, suggestion);
        return true;
    }

    /**
	 * Removes suggestion submitted by committee member of a camp's suggestions list
	 * 
     * @param campID ID of the camp
     * @param ccm Committee Member that submitted the suggestion
     * @param suggestion Suggestion submitted 
     * @return True if suggestion is removed, False otherwise
	 */
    public boolean RemoveSuggestion(int campID, CCM ccm, Suggestions suggestion)
    {
        return camps.get(campID).RemoveSuggestion(ccm, suggestion);
    }

    /**
	 * Add enquiry submitted by a student to a camp's enquiries list
	 * 
     * @param campID ID of the camp
     * @param student Student that submitted the enquiry
     * @param enquiry Enquiry submitted 
     * @return True once enquriy is added
	 */
    public boolean AddEnquiry(int campID, Student student, Enquiries enquiry)
    {
        camps.get(campID).AddEnquiry(student, enquiry);
        return true;
    }

    /**
	 * Removes enquiry submitted by student of a camp's enquiries list
	 * 
     * @param campID ID of the camp
     * @param ccm Student that submitted the enquiry
     * @param enquiry Enquiry submitted 
     * @return True if enquiry is removed, False otherwise
	 */
    public boolean RemoveEnquiry(int campID, Student student, Enquiries enqury)
    {
        return camps.get(campID).RemoveEnquiry(student, enqury);
    }


    /**
	 * Checks if the user is already registered in this camp as an attendee
	 * 
     * @param campID ID of the camp
     * @param name Name of the user to check
     * @return True if user is already registered in this camp, False otherwise
	 */
    public Boolean AlreadyRegistered(int campID, String name)
    {
        ArrayList<Student> attendees = camps.get(campID).GetAttendees();

        for(Student s : attendees)
            if(s.name.equals(name)) return true;

        return false;
    }

    /**
	 * Checks if the user is already registered in this camp as a committee member
	 * 
     * @param campID ID of the camp
     * @param name Name of the user to check
     * @return True if user is already registered in this camp, False otherwise
	 */
    public Boolean AlreadyCommittee(int campID, String name)
    {
        ArrayList<CCM> attendees = camps.get(campID).GetCommitteeList();

        for(CCM c : attendees)
            if(c.name.equals(name)) return true;

        return false;
    }

    /**
	 * Sorts the camp by alphabetical order
	 * 
     * @param camps List of camps to sort
	 */
    public void SortCampsByName(ArrayList<Camp> camps) 
    {
        Collections.sort(camps, new Comparator<Camp>() 
        {
            @Override
            public int compare(Camp camp1, Camp camp2) 
            {
                if (camp1 == null && camp2 == null)
                    return 0;
                else if (camp1 == null)
                    return 1; // null is considered greater (placed at the end)
                else if (camp2 == null)
                    return -1; // null is considered greater (placed at the end)
                else
                    return camp1.GetCampName().compareTo(camp2.GetCampName()); // Compare camp names in alphabetical order
            }
        });
    }

    /**
	 * Reassign CampID based on current order of array
	 * 
     * @param camps List of camps to reassigned ID values
	 */
    public void ReassignCampIDs(ArrayList<Camp> camps) 
    {
        int id = 0;
        for (Camp camp : camps) 
        {
            if (camp != null) 
                camp.SetCampID(id++);
        }
    }
        
}
    

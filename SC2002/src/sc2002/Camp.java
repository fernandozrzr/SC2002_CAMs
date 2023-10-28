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
    private ArrayList<CampCommiteeMember> committeeMembers;
    private ArrayList<Enquiries> enquires;
    private ArrayList<Suggestions> suggestions;
    private static ArrayList<Camp> camps;

    public Camp()
    {

    }

    public void AddAttendees(Student student)
    {
        //No more Slots
        if(attendees.size() == totalSlots)
        {
            System.out.println("This camp has been fully signed up.");
            return;
        }

        attendees.add(student);
        System.out.println("You have succuessfully signed up for the " + campName + " camp.");
    }

    public void RemoveAttendee(student student)
    {
        //Student did not sign up for this camp
        if(!attendees.contains(student))
        {
            System.out.println("You have not signed up for this camp.");
            return;
        }

        attendees.remove(student);
        System.out.println("You have succuessfully been removed from the " + campName + " camp. Note: You will not be able to rejoin this camp.");
    }

    public void ToggleVisibility(User user, boolean state)
    {
        //User is not staff so cannot change
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to toggle the visibility.");
            return;
        }

        visibility = state;
        if(visibility)
            System.out.println(campName + " is now visible to all students.");
        else
            System.out.println(campName + " is now hidden from all students.");
    }

    public void ViewAllCamps()
    {
        for(int i = 0; i < camps.size(); ++i)
        {
            System.out.println("(" + i + 1 + ") " + camps.get(i).GetCampName());
        }
    }

    public void ViewCampDetails()
    {
        int slotsLeft = totalSlots - attendees.size();
        int memberSlotLeft = committeeSlots - committeeMembers.size();

        System.out.println("CampName: " + campName);
        System.out.println("Date: " + date);
        System.out.println("Description: " + description);
        System.out.println("Location: " + location);
        System.out.println("Staff In Charge: " + staffInCharge);
        System.out.println("Faculty: " + userGrp);
        System.out.println("Registeration Closing Date: " + registerCloseDate);
        System.out.println("No. of slots: " + attendees.size() + "/" + totalSlots + " (" + slotsLeft + " slots are available)");
        System.out.println("No. of Committee Slots: " + committeeMembers.size() + "/" + committeeSlots + " (" + memberSlotLeft + " slots are available)");
    }

    public static void CreateCamp(User user)
    {
        //User is not staff so cannot create
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to create a camp.");
            return;
        }

        Camp newCamp = new Camp();
        Camp.camps.add(newCamp);
        System.out.println("Camp has been created.");
    }

    public void EditCamp()
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return;
        }   
    }

    public void DeleteCamp(User user)
    {
        //User is not staff so cannot delete
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to delete this camp.");
            return;
        }

        System.out.println(campName + " have been deleted.");
        camps.remove(this);
        
    }

    public String GetCampName()
    {
        return campName;
    }

    public ArrayList<Student> GetAttendees()
    {
        return attendees;
    }

    public ArrayList<CampCommiteeMember> GetCommitteeMembers()
    {
        return committeeMembers;
    }

    public ArrayList<Enquiries> GetEnquires()
    {
        return enquires;
    }

    public ArrayList<Enquiries> GetSuggestions()
    {
        return suggestions;
    }
}

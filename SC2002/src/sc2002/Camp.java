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

    public void RemoveAttendee(Student student)
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

    public void AddCommitteeMembers(Student student)
    {
        
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
            System.out.println("(" + i + 1 + ") " + camps.get(i).GetCampName());
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

    public static Camp CreateCamp(User user, String campName, String date, String registerCloseDate,
                            String userGrp, String location, String description, String staffInCharge,
                            int totalSlots, int committeeSlots, boolean visbility)
    {
        //User is not staff so cannot create
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to create a camp.");
            return null;
        }

        Camp newCamp = new Camp()
                            .campName(user, campName)
                            .date(user, date)
                            .registerCloseDate(user, registerCloseDate)
                            .userGrp(user, userGrp)
                            .location(user, location)
                            .description(user, description)
                            .staffInCharge(user, staffInCharge)
                            .totalSlots(user, totalSlots)
                            .committeeSlots(user, committeeSlots)
                            .visbility(user, visbility);
        Camp.camps.add(newCamp);
        System.out.println("Camp has been created.");
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

    //Methods used to Edit Camp details
     public Camp campName(User user, string campName)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.campName = campName;
        return this;
    }

    public Camp date(User user, string date)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.date = date;
        return this;
    }

    public Camp registerCloseDate(User user, string registerCloseDate)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.registerCloseDate = registerCloseDate;
        return this;
    }
    
    public Camp userGrp(User user, string userGrp)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.userGrp = userGrp;
        return this;
    }
    
    public Camp location(User user, string location)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.location = location;
        return this;
    }
    
    public Camp description(User user, string description)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.description = description;
        return this;
    }
    
    public Camp staffInCharge(User user, string staffInCharge)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.staffInCharge = staffInCharge;
        return this;
    }
    
    public Camp totalSlots(User user, int totalSlots)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.camtotalSlotspName = totalSlots;
        return this;
    }
    
    public Camp committeeSlots(User user, int committeeSlots)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.committeeSlots = committeeSlots;
        return this;
    }

    public Camp visbility(User user, boolean visbility)
    {
        //User is not staff so cannot edit
        if (!(user instanceof Staff))
        {
            System.out.println("You do not have the authorisation to edit this camp.");
            return null;
        } 

        this.visbility = visbility;
        return this;
    }
}

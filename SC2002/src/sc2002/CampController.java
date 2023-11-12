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

    public CampController()
    {
        camps = new ArrayList<Camp>();
    }

    public ArrayList<Camp> GetCamps()
    {
        return camps;
    }

    public boolean AddAttendee(Camp camp, Student student)
    {
        return camp.AddAttendee(student);
    }

    public boolean RemoveAttendee(Camp camp, Student student)
    {
        return camp.RemoveAttendee(student);
    }

    public boolean AddCommitteeMember(Camp camp, CCM ccm)
    {
        return camp.AddCommitteeMember(ccm);
    }

    public void ToggleVisibility(Camp camp, boolean state)
    {
        camp.SetVisibility(state);
    }

    public boolean DeleteCamp(Camp camp)
    {
        if(camps.contains(camp))
        {
            camps.remove(camp);
            return true;
        }

        return false;
    }

    public Camp AddCamp(String campName, String date, String registerCloseDate, String userGrp, String location, 
                            String desc, String staffInCharge, int totalSlots, int committeeSlots, boolean visibility)
    {
        Camp camp = new Camp(campName, date, registerCloseDate, userGrp, 
                location, desc, staffInCharge, totalSlots, committeeSlots, visibility);
        camps.add(camp);
        return camp; //Added for for StaffController
    }
}

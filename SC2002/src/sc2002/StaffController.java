package sc2002;

import java.util.ArrayList;
import java.util.HashMap;

public class StaffController {
	
	private CampController campManager;
	private StaffView staffViewManager;
	private static StaffController instance = null;
	 
	public static StaffController GetInstance() {
        if (instance == null)
            instance = new StaffController();
        return instance;
    }
	
	public StaffController() {
		this.campManager = CampController.GetInstance();
		this.staffViewManager = new StaffView();
    }
	
	public void ViewAllCamp()
    {
		staffViewManager.DisplayAllCamps();
    }
	public Camp CreateCamp(String campName, String date, String closedate, String userGrp, String location, String description, String staffIC, int totalSlots, int CommitteeSlots, boolean Visibility)
    {
		Camp newcamp=campManager.AddCamp(campName, date, closedate, userGrp, location, description, staffIC, totalSlots, CommitteeSlots, Visibility);
		return newcamp;
    }
	public void EditCamp(int campID)
    {
		
    }
	public void DeleteCamp(int campID)
    {
		campManager.DeleteCamp(campID);
    }
	public void ToggeVisibility(int campID, boolean state)
    {
		campManager.ToggleVisibility(campID, state);
    }
	public void ViewSuggestions(int CampID)
    {
		HashMap<CCM, ArrayList<Suggestions>> map = campManager.GetCamps().get(CampID).getSuggestion();
		staffViewManager.DisplayAllSuggestions(map);
    }
	public void ApproveSuggestions(int index)
    {
    }
	public void ViewEnquiries(int CampID)
    {	
		HashMap<Student, ArrayList<Enquiries>> map = campManager.GetCamps().get(CampID).getEnquiries();
		staffViewManager.DisplayAllEnquiries(map);
    }
	public void ReplyEnquiries(String reply)
    {
    }
	public void GenerateList()
    {
    }
	public void GenerateReport(Camp camp)
    {
    }
	
}

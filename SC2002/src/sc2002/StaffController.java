package sc2002;

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
		this.campManager = new CampController();
		this.staffViewManager = new StaffView();
    }
	
	public void ViewAllCamp()
    {
		staffViewManager.DisplayAllCamps(campManager.GetCamps());
    }
	public Camp CreateCamp(String campName, String date, String closedate, String userGrp, String location, String description, String staffIC, int totalSlots, int CommitteeSlots, boolean Visibility)
    {
		Camp newcamp=campManager.AddCamp(campName, date, closedate, userGrp, location, description, staffIC, totalSlots, CommitteeSlots, Visibility);
		return newcamp;
    }
	public void EditCamp()
    {
		
    }
	public void DeleteCamp(Camp camp)
    {
		campManager.DeleteCamp(camp);
    }
	public void ToggeVisibility(Camp camp, boolean state)
    {
		campManager.ToggleVisibility(camp, state);
    }
	public void ViewSuggestions(Camp camp)
    {
		staffViewManager.DisplayAllSuggestions(camp);
    }
	public void ApproveSuggestions(int index)
    {
    }
	public void ViewEnquiries(Camp camp)
    {
		staffViewManager.DisplayAllEnquiries(camp);
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

package sc2002;

public class StaffController {
	
	private CampController campManager;
	private StaffView staffViewManager;
	public StaffController() {
		this.campManager = new CampController();
		this.staffViewManager = new StaffView();
    }
	
	public void ViewAllCamp()
    {
		staffViewManager.DisplayAllCamps(campManager.GetCamps());
    }
	public void CreateCamp(String campName, String date, String closedate, String userGrp, String location, String description, String staffIC, int totalSlots, int CommitteeSlots, boolean Visibility)
    {
		campManager.AddCamp(campName, date, closedate, userGrp, location, description, staffIC, totalSlots, CommitteeSlots, Visibility);
    }
	public void EditCamp()
    {
		
    }
	public void DeleteCamp(Camp camp)
    {
		campManager.DeleteCamp(null);
    }
	public void ToggeVisibility(Camp camp, boolean state)
    {
		campManager.ToggleVisibility(camp, state);
    }
	public void ViewSuggestions()
    {
    }
	public void ApproveSuggestions()
    {
    }
	public void ViewEnquiries()
    {
    }
	public void ReplyEnquiries()
    {
    }
	public void GenerateList()
    {
    }
	public void GenerateReport()
    {
    }
	
}

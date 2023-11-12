package sc2002;

import java.util.ArrayList;

public class StaffView implements EnquiriesView, CampView, SuggestionsView{
	public StaffView() {
		super();
	}
	public void DisplayAllCamps(ArrayList<Camp> camp) {
		 for (Camp campname : camp) {
			 System.out.println(campname.GetCampName());
	}}

	@Override
	public void DisplayMySuggestions(CCM ccm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayAllEnquiries(Camp camp) {
		// TODO Auto-generated method stub
		
	}

	public void DisplayMyCamps(ArrayList<Camp> camp) {
	}
}

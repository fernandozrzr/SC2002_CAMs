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
	public String DisplaySuggestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DisplayMyCamps(ArrayList<Camp> camps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayAllEnquiries() {
		// TODO Auto-generated method stub
		
	}
}

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
	public void DisplayAllSuggestions(Camp camp) {
		for (Camp campname : camp) {
			 System.out.println(campname.GetSuggestion());
		}
		
	}

	@Override
	public void DisplayAllEnquiries(Camp camp) {
		for (Camp campname : camp) {
			 System.out.println(campname.GetEnquires());
		}
	}

	public void DisplayMyCamps(ArrayList<Camp> camp) {
		for (Camp campname : camp) {
			 System.out.println(campname.GetCampName());
		}
	}
}

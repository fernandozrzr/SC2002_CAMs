package sc2002;

import java.util.ArrayList;

public class StaffView implements EnquiriesView, CampView, SuggestionsView{
	public StaffView() {
		super();
	}

	@Override
	public void DisplaySuggestions(Camp camp) {
		System.out.println("");
	}
	@Override
	public void DisplayAllCamps(Camp camp) {
		System.out.println("");
	}
	@Override
	public void DisplayMyCamp(Camp camp) {
		System.out.println("");
	}
}

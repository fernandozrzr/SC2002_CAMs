package sc2002;

import java.util.ArrayList;

public class Staff extends User {
  private ArrayList<Camp> createdCamps;
	
	public Staff() {
		ArrayList<Camp> createdCamps = new ArrayList<>();
	}
	
	public String ViewMyCreatedCamp() {
		for (Camp camp : createdCamps) {
			System.out.println(camp);
		}
	}
	
	public void GenerateList(Camp) {
		
	}
	
	public void GenerateReport(Camp) {
		
	}
}

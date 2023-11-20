//CCM.java
package sc2002;

import java.util.ArrayList;
import java.util.Scanner;
public class CCM extends Student{
    
    private int myPoints = 0;
    ArrayList<Suggestions> mySuggestions;
    Scanner sc = new Scanner(System.in);
    
    public CCM(String name, String userID, String faculty, ArrayList<Enquiries> myEnquiries, ArrayList<Camp> registeredCamps, int ccmID)
    {
    	super(name, userID, faculty);
        this.myPoints = 0;
        this.myEnquiries  = myEnquiries;
        this.registeredCamps = registeredCamps;
        this.ccmID = ccmID;
        this.mySuggestions = new ArrayList<Suggestions>();
    }

	public int GetPoints(){
        return myPoints;
    }

    public void SetPoints(int myPoints) {
        this.myPoints = myPoints;
    }

    public ArrayList<Suggestions> getMySuggestions() {
        return mySuggestions;
    }
    public void AddMySuggestion(Suggestions suggestion) {
        mySuggestions.add(suggestion);
    }

    public void DeleteMySuggestion(Suggestions suggestion) {
        mySuggestions.remove(suggestion);
    }

    public void EditMySuggestion(Suggestions oldSuggestion, Suggestions newSuggestion) {
        int index = mySuggestions.indexOf(oldSuggestion);
        if (index != -1) {
            mySuggestions.set(index, newSuggestion);
        } else {
            System.out.println("Suggestion not found for editing.");
        }
    }

    public Suggestions FindSuggestion(int suggestionID)
    {
        for(int i = 0; i < mySuggestions.size(); ++i)
        {
            if(mySuggestions.get(i).GetID() == suggestionID)
                return mySuggestions.get(i);
        }

        System.out.println("Suggestion ID: " + suggestionID + " does not exist");
        return null;
    }

    
}

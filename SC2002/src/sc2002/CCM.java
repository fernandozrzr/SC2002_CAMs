//CCM.java
package sc2002;

import java.util.ArrayList;
import java.util.Scanner;
public class CCM {
    
    private int myPoints = 0;
    ArrayList<Suggestions> mySuggestions;
    Scanner sc= new Scanner(System.in);
    
    public CCM(int myPoints, ArrayList<Suggestions> mySuggestions){
        this.myPoints = myPoints;
        mySuggestions = new ArrayList<Suggestions>();
    }

    public int getPoints(){
        return myPoints;
    }

    public void setPoints(int myPoints) {
        this.myPoints = myPoints;
    }

    public ArrayList<Suggestions> getMySuggestions() {
        return mySuggestions;
    }
    public void AddSuggestion(Suggestions suggestion) {
        mySuggestions.add(suggestion);
    }

    public void DeleteSuggestion(Suggestions suggestion) {
        mySuggestions.remove(suggestion);
    }

    public void EditSuggestion(Suggestions oldSuggestion, Suggestions newSuggestion) {
        int index = mySuggestions.indexOf(oldSuggestion);
        if (index != -1) {
            mySuggestions.set(index, newSuggestion);
        } else {
            System.out.println("Suggestion not found for editing.");
        }
    }
}

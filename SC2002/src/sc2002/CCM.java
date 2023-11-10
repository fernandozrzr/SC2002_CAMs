//CCM.java
package sc2002;

import java.util.ArrayList;

public class CCM {
    
    private int myPoints = 0;
    private int suggestionslot = 5;
    ArrayList<Suggestions> mySuggestions;
    
    public CCM(int myPoints, ArrayList<Suggestions> mySuggestions){
        this.myPoints = myPoints;
        mySuggestions = new ArrayList<Suggestions>(suggestionslot);
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

    public void setMySuggestions(ArrayList<Suggestions> mySuggestions) {
        this.mySuggestions = mySuggestions;
    }
}

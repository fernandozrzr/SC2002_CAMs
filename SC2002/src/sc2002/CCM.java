package sc2002;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a CCM (Campus Community Manager) who is a type of Student with additional functionalities.
 * Extends the Student class.
 * 
 * @author George Lai
 * @version 1.0
 * @since 25/11/2023
 */
public class CCM extends Student {
    
    /**
     * The points accumulated by the CCM.
     */
    private int myPoints = 0;

    /**
     * List of suggestions made by the CCM.
     */
    ArrayList<Suggestions> mySuggestions;

    /**
     * Scanner object for user input.
     */
    Scanner sc = new Scanner(System.in);
    
    /**
     * Constructs a CCM object with the given parameters.
     * 
     * @param name the name of the CCM
     * @param userID the user ID of the CCM
     * @param faculty the faculty to which the CCM belongs
     * @param myEnquiries list of enquiries made by the CCM
     * @param registeredCamps list of camps registered by the CCM
     * @param ccmID the unique identifier for the CCM
     */
    public CCM(String name, String userID, String faculty, ArrayList<Enquiries> myEnquiries, 
               ArrayList<Camp> registeredCamps, int ccmID) {
        super(name, userID, faculty);
        this.myPoints = 0;
        this.myEnquiries = myEnquiries;
        this.registeredCamps = registeredCamps;
        this.ccmID = ccmID;
        this.mySuggestions = new ArrayList<Suggestions>();
    }

    /**
     * Gets the points accumulated by the CCM.
     * 
     * @return the points of the CCM
     */
    public int GetPoints() {
        return myPoints;
    }

    /**
     * Sets the points for the CCM.
     * 
     * @param myPoints the points to set
     */
    public void SetPoints(int myPoints) {
        this.myPoints = myPoints;
    }

    /**
     * Gets the list of suggestions made by the CCM.
     * 
     * @return the list of suggestions
     */
    public ArrayList<Suggestions> GetMySuggestions() {
        return mySuggestions;
    }

    /**
     * Adds a suggestion to the CCM's list of suggestions.
     * 
     * @param suggestion the suggestion to add
     */
    public void AddMySuggestion(Suggestions suggestion) {
        mySuggestions.add(suggestion);
    }

    /**
     * Deletes a suggestion from the CCM's list of suggestions.
     * 
     * @param suggestion the suggestion to delete
     */
    public void DeleteMySuggestion(Suggestions suggestion) {
        mySuggestions.remove(suggestion);
    }

    /**
     * Finds a suggestion in the CCM's list based on the suggestion ID.
     * 
     * @param suggestionID the ID of the suggestion to find
     * @return the suggestion if found, null otherwise
     */
    public Suggestions FindSuggestion(int suggestionID) {
        for (int i = 0; i < mySuggestions.size(); ++i) {
            if (i == suggestionID) {
                return mySuggestions.get(i);
            }
        }

        System.out.println("Suggestion ID: " + suggestionID + " does not exist");
        return null;
    }
}

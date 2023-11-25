package sc2002;

/**
 * Represents a suggestion made by a user or a committee member.
 * Each suggestion has an ID, suggestion text, and a status indicating whether it is completed.
 * 
 * @author George Lai
 * @version 1.0
 * @since 25/11/23
 */
public class Suggestions {

    private int id;
    private String suggestion;
    private boolean status;

    /**
     * Constructor for creating a Suggestions object.
     * 
     * @param id         The ID of the suggestion.
     * @param suggestion The text of the suggestion.
     * @param status     The status of the suggestion (true if completed, false otherwise).
     */
    public Suggestions(int id, String suggestion, boolean status) {
        this.id = id;
        this.suggestion = suggestion;
        this.status = status;
    }

    /**
     * Gets the text of the suggestion.
     * 
     * @return The text of the suggestion.
     */
    public String GetSuggestion() {
        return suggestion;
    }

    /**
     * Sets the text of the suggestion.
     * 
     * @param suggestion The new text of the suggestion.
     */
    public void SetSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    /**
     * Checks if the suggestion is marked as completed.
     * 
     * @return True if the suggestion is completed, false otherwise.
     */
    public boolean IsStatus() {
        return status;
    }

    /**
     * Gets the ID of the suggestion.
     * 
     * @return The ID of the suggestion.
     */
    public int GetID() {
        return id;
    }

    /**
     * Sets the status of the suggestion.
     * 
     * @param status The new status of the suggestion.
     */
    public void SetStatus(boolean status) {
        this.status = status;
    }
}

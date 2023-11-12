//suggestions.java
package sc2002;

public class Suggestions {

    private String suggestion;
    private String suggestedBy;
    private boolean status;

    public Suggestions(String suggestion, String suggestedBy) {
        this.suggestion = suggestion;
        this.suggestedBy = suggestedBy;
        this.status = false;
    }
    
    // Getter and setter methods for the attributes
    public void setSuggestions(String suggestion, String suggestedBy, boolean status) {
        this.suggestion = suggestion;
        this.suggestedBy = suggestedBy;
        this.status = status;
    }

    public String getSuggestion(){
        return suggestion;
    }
    public String getSuggestedBy(){
        return suggestedBy;
    }
    public boolean isStatus(){
        return status;
    }
}
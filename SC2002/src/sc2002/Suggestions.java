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
    public setSuggestions(String suggestion, String suggestedBy, boolean status) {
        this.suggestion = suggestion;
        this.suggestedBy = suggestedBy;
        this.status = status;
    }

    public getSuggestion(){
        return suggestion;
    }
    public getSuggestedBy(){
        return suggestedBy;
    }
    public isStatus(){
        return status;
    }
}
package sc2002;

import java.util.ArrayList;

public class SuggestionsController {    
    private static SuggestionsController instance = null;
    private ArrayList<Suggestions> suggestions;

    public static SuggestionsController getInstance() {
        if (instance == null)
            instance = new SuggestionsController();

        return instance;
    }

    private SuggestionsController() {
        // Initialize Suggestions list
        suggestions = new ArrayList<>();
    }

    public ArrayList<Suggestions> getSuggestions() {
        return suggestions;
    }

    // Add an enquiry to the Suggestions list
    public boolean AddSuggestion(Suggestions suggestion) {
        return suggestions.add(suggestion);
    }
}

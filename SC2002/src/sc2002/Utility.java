package sc2002;

/**
 * Utility Class with static funtions to help make codes cleaner
 * 
 * @author Edward Seah
 * @version 1.0
 * @since 24/11/2023
 */
public class Utility 
{
    /**
    * Checks if the string input from the user is a valid choice
    * 
    * @param userChoice User's input
    * @param choices List of valid choices to check against userChoice
    * @return True if user's input is valid, False otherwise
    */
    public static boolean IsValidChoice(String userChoice, String[] choices)
    {
        for(String choice : choices)
        {
            if(userChoice.equals(choice)) return true;
        }

        return false;
    }
}

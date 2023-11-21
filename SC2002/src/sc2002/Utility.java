package sc2002;

public class Utility 
{
    public static boolean IsValidChoice(String userChoice, String[] choices)
    {
        for(String choice : choices)
        {
            if(userChoice.equals(choice)) return true;
        }

        return false;
    }
}

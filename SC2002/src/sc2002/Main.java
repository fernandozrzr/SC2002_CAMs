package sc2002;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Auth();
    }

    /////////////////////////////////////////////////           TEMP FUNCTION           //////////////////////////////////////////////////
    /////////////////////////////////////////////////   REPLACE ONCE LOGIN IS COMPLETED //////////////////////////////////////////////////
    public static void Auth()
    {
        // String userName, password =  "";

        // Scanner input = new Scanner(System.in);

        // System.out.print("Enter UserName: ");
        // userName = input.nextLine();

        // System.out.print("Enter Password: ");
        // password = input.nextLine();

        StudentController.GetInstance().StudentMenu();
    }
}

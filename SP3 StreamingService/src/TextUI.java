import java.util.ArrayList;
import java.util.Scanner;

public class TextUI
{

    Scanner scan = new Scanner(System.in);

    public void displayMessage(String msg)
    {
        System.out.println(msg);
    }

    public String getUserInput()
    {
        return scan.nextLine();
    }
    // Displays a message before taking the users input
    public String getUserInput(String msg)
    {
        System.out.println(msg);
        return scan.nextLine();
    }

    // Displays a message, and a list of options. Take an input from the user afterwards
    public String getUserInput(String msg, ArrayList<IMedia> options)
    {

        System.out.println(msg);
        for(int i = 0; i<options.size(); i++)
        {
            System.out.println(i + ". "+options.get(i));
        }
        return scan.nextLine();
    }
}
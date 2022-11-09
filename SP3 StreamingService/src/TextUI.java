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

    // Displays a menu of Media, and returns the selected. Returns null if user inputs 'Q'
    public IMedia mediaMenu(ArrayList<IMedia> options)
    {
        boolean showingChoice = true;
        boolean optionsActive = true;
        while(optionsActive)
        {
            String choice  = getUserInput("Search results. Enter number to choose or 'Q' to return ", options);

            if (choice.equalsIgnoreCase("Q")) { break; }

            while (showingChoice)
            {
                try
                {
                    int choiceInt = Integer.parseInt(choice);

                    if (choiceInt >= 0 && choiceInt < options.size())
                    {
                        IMedia show = options.get(choiceInt);
                        return show;
                    }

                    else
                    {
                        displayMessage("Please enter a valid number on the list");
                        break;
                    }
                }

                catch (NumberFormatException e)
                {
                    displayMessage("Enter a valid number on the list, or 'Q' to return");
                    break;
                }
            }
        }
        return null;
    }
}
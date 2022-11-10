import java.io.IOException;
import java.net.URISyntaxException;
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
            System.out.println(i + 1 + ". "+options.get(i));
        }
        return scan.nextLine();
    }

    // Displays a menu of Media, and returns the selected. Returns null if user inputs 'Q'
    public void mediaMenu(ArrayList<IMedia> options, User currentUser)
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
                    choiceInt--;

                    if (choiceInt >= 0 && choiceInt < options.size())
                    {
                        IMedia show = options.get(choiceInt);

                        String playChoice = getUserInput("Do you want to play " + show.getName() + "? (Y/N)");

                        if (playChoice.equalsIgnoreCase("Y"))
                        {
                            currentUser.addShowsSeen(show.getName());

                                show.play();

                           // displayMessage("Playing " + show.getName() + ". Press any key to return"); // test
                            getUserInput();
                            break;
                        }

                        else break;
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

                catch (URISyntaxException u)
                {
                    displayMessage("URI failure");
                    break;
                }

                catch (IOException i)
                {
                    displayMessage("IO Failure");
                }
            }
        }
    }
}
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextUI
{
    private Scanner scan = new Scanner(System.in);

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
        displayMessage("*************************************************************");
        for(int i = 0; i<options.size(); i++)
        {
            System.out.println(i + 1 + ". "+options.get(i));
        }
        displayMessage("*************************************************************");
        System.out.println(msg);

        return scan.nextLine();
    }

    // Displays a menu of Media, and returns the selected. Returns null if user inputs 'Q'
    public void mediaMenu(ArrayList<IMedia> options, User currentUser, boolean personalList)
    {
        boolean showingChoice = true;
        boolean optionsActive = true;
        while(optionsActive)
        {
            String choice ="";

            if (options.size() < 1)
            {
                choice = getUserInput("No results. Enter 'Q' to return");
            }

            else choice  = getUserInput("Search results. Enter number to choose or 'Q' to return ", options);

            if (choice.equalsIgnoreCase("Q")) { break; }

            while (showingChoice)
            {
                try
                {
                    int choiceInt = Integer.parseInt(choice);
                    choiceInt--;

                    if (choiceInt >= 0 && choiceInt < options.size() && !personalList)
                    {
                        IMedia show = options.get(choiceInt);

                        String playChoice = getUserInput("Do you want to play " + show.getName() + "? (Y/Q).\nEnter 'F' to add to watchlist");

                        if (playChoice.equalsIgnoreCase("F"))
                        {
                            boolean alreadyContains = false;

                            for (String name: currentUser.getFavouriteShows())
                            {
                                if (show.getName().equals(name))
                                {
                                    alreadyContains = true;
                                }
                            }

                            if (!alreadyContains)
                            {
                                currentUser.addFavouriteShows(show.getName());
                                displayMessage(show.getName() + " Added to watchlist.");
                            }

                            else displayMessage(show.getName() + " is already on your watchlist.");
                        }

                        if (playChoice.equalsIgnoreCase("Y"))
                        {
                            currentUser.addShowsSeen(show.getName());

                            show.play();

                            getUserInput();
                            break;
                        }

                        if (playChoice.equalsIgnoreCase("Q")) { break; }
                    }

                    else if (choiceInt >= 0 && choiceInt < options.size() && personalList)
                    {
                        IMedia show = options.get(choiceInt);

                        String playChoice = getUserInput("Do you want to play " + show.getName() + "? (Y/Q).\nEnter 'R' to remove from list");

                        if (playChoice.equalsIgnoreCase("R"))
                        {
                            currentUser.removeFavouriteShow(show.getName());
                            options.remove(show);
                            displayMessage(show.getName() + " Removed from list");
                        }

                        if (playChoice.equalsIgnoreCase("Y"))
                        {
                            currentUser.addShowsSeen(show.getName());

                            show.play();

                            getUserInput();
                            break;
                        }

                        if (playChoice.equalsIgnoreCase("Q")) { break; }
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
                    break;
                }
            }
        }
    }
}
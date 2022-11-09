import java.util.ArrayList;

public class MainMenu
{
    User currentUser;
    ArrayList<IMedia> media = new ArrayList<>();
    public TextUI textUI = new TextUI();

    public SearchFunction searchFunction =new SearchFunction(currentUser, media);

    public MainMenu(User currentUser)
    {
        this.currentUser = currentUser;
    }

    public void runMainMenu()
    {

        createMedia();

        textUI.displayMessage("Welcome to fedFlix, " + currentUser.getUsername() + "!");

        boolean running = true;
        while (running)
        {

            textUI.displayMessage("Press 1 to view all media\npress 2 to view all series\npress 3 to view all movies\n" +
                    "press 4 to view your watchlist\npress 5 to view your history\npress 6 to search for media\npress 7 to logout");

            String input = textUI.getUserInput();

            if(input.equals("1"))
            {
                searchFunction.viewAllMedia();
            }

            if (input.equals("2"))
            {
                searchFunction.viewAllSeries();
            }

            if (input.equals("3"))
            {
                searchFunction.viewAllMovies();
            }

            if (input.equals("4"))
            {
                searchFunction.viewWatchlist();
            }

            if (input.equals("5"))
            {
                searchFunction.viewHistory();
            }

            if (input.equals("6"))
            {
                input = textUI.getUserInput("Search media: ");
                ArrayList<IMedia> options = searchFunction.searchMedia(input);
                boolean showingChoice = true;
                boolean choicesActive = true;
                while(choicesActive)
                {
                    String choice  = textUI.getUserInput("Search results. Enter number to choose or 'Q' to return ", options);

                    if (choice.equalsIgnoreCase("Q")) { break; }

                    while(showingChoice)
                    {
                        try
                        {
                            int choiceInt = Integer.parseInt(choice);

                            if (choiceInt >= 0 && choiceInt < options.size())
                            {
                                IMedia show = options.get(choiceInt);
                                String playChoice = textUI.getUserInput("Do you want to play " + show.getName() + "? (Y/N)");

                                if (playChoice.equalsIgnoreCase("Y"))
                                {
                                    // show.play();
                                    // user.addShow
                                }

                                else { break; }
                            }

                            else
                            {
                                textUI.displayMessage("Please enter a valid number on the list");
                                break;
                            }
                        }

                        catch (NumberFormatException e)
                        {
                            textUI.displayMessage("Enter A number on the list, or 'Q' to return to menu");
                        }
                    }
                }
            }

            if (input.equals("7"))
            {
                logOut();
            }

        }
    }

    private void createMedia()
    {
        FileIO fileIO = new FileIO();
        ArrayList<String> moviedata = new ArrayList<>();
        ArrayList<String> seriesdata = new ArrayList<>();

        seriesdata = fileIO.readSeriesData();
        moviedata = fileIO.readMovieData();

        for (String s : moviedata)
        {
            String[] values = s.split(";");
            String[] categories = values[2].split(",");
            Movie movie = new Movie(values[0], values[1], categories, values[3]);
            media.add(movie);
        }

        for (String s : seriesdata)
        {
            String[] values = s.split(";");
            String[] categories = values[2].split(",");
            String[] seasons = values[4].split(",");
            Series series = new Series(values[0], values[1], categories, values[3], seasons);
            media.add(series);
        }


    }

    // logout method to return to login screen
    protected void logOut()
    {
        FedFlix fedFlix = new FedFlix();
        fedFlix.runFedFlix();
    }

}

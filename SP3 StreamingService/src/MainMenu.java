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
                    "press 4 to view your watchlist\npress 5 to view your history\npress 6 to search for media\npress 7 to choose Category\npress 8 to logout");

            String input = textUI.getUserInput();

            // view all media
            if(input.equals("1"))
            {
                searchFunction.viewAllMedia();
            }

            // view all series
            if (input.equals("2"))
            {
                searchFunction.viewAllSeries();
            }

            // view all movies
            if (input.equals("3"))
            {
                searchFunction.viewAllMovies();
            }

            // view watchlist(favorites)
            if (input.equals("4"))
            {
                searchFunction.viewWatchlist();
            }

            // view history(showsSeen)
            if (input.equals("5"))
            {
                searchFunction.viewHistory();
            }

            // search for media
            if (input.equals("6"))
            {

                    input = textUI.getUserInput("Search media: ");
                    ArrayList<IMedia> options = searchFunction.searchMedia(input);
                while (true)
                {
                    IMedia show = textUI.mediaMenu(options);

                    if (show == null) { break; }

                    while (true)
                    {
                        String playChoice = textUI.getUserInput("Do you want to play " + show.getName() + "? (Y/N)");

                        if (playChoice.equalsIgnoreCase("Y"))
                        {
                            // currentUser.addShowsSeen(show);
                            // show.play();
                            textUI.displayMessage("Playing " + show.getName() + ". Press any key to return"); // test
                            textUI.getUserInput();
                            break;
                        }
                        else break;
                    }
                }
            }

            // choose Category
            if(input.equals("7"))
            {
                searchFunction.viewAllCategory();
            }

            if (input.equals("8"))
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

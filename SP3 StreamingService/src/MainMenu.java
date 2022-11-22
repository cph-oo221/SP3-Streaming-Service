import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainMenu
{
    private ArrayList<User> users;
    private User currentUser;
    public ArrayList<IMedia> media = new ArrayList<>();
    public TextUI textUI = new TextUI();
    public SearchFunction searchFunction = new SearchFunction(currentUser, media);

    public MainMenu(User currentUser, ArrayList<User> users)
    {
        this.currentUser = currentUser;
        this.users = users;
    }

    public void runMainMenu()
    {

        createMedia();

        textUI.displayMessage("Welcome to fedFlix, " + currentUser.getUsername() + "!");

        boolean running = true;
        while (running)
        {

            textUI.displayMessage("""
                    Press 1 to view all media
                    press 2 to view all series
                    press 3 to view all movies
                    press 4 to view your watchlist
                    press 5 to view your history
                    press 6 to search for media
                    press 7 to choose Category
                    press 8 to logout""");

            String input = textUI.getUserInput();

            // view all media
            if(input.equals("1"))
            {
                ArrayList<IMedia> options = searchFunction.viewAllMedia();
                textUI.mediaMenu(options, currentUser, false);
            }

            // view all series
            if (input.equals("2"))
            {
                ArrayList<IMedia> options = searchFunction.viewAllSeries();
                textUI.mediaMenu(options, currentUser, false);
            }

            // view all movies
            if (input.equals("3"))
            {
                ArrayList<IMedia> options = searchFunction.viewAllMovies();
                textUI.mediaMenu(options, currentUser, false);
            }

            // view watchlist(favorites)
            if (input.equals("4"))
            {
               ArrayList<IMedia> options = new ArrayList<>();

               ArrayList<String> userFaves = currentUser.getFavouriteShows();

                for (String s: userFaves)
                {
                    for (IMedia m: media)
                    {
                        if (s.equals(m.getName()))
                        {
                            options.add(m);
                        }
                    }
                }
                    textUI.displayMessage("Your list of favourites.");
                    textUI.mediaMenu(options, currentUser, true);
            }

            // view history(showsSeen)
            if (input.equals("5"))
            {
                ArrayList<IMedia> options = new ArrayList<>();

                ArrayList<String> userWatched = currentUser.getShowsSeen();

                for (String s: userWatched)
                {
                    for (IMedia m: media)
                    {
                        if (s.equals(m.getName()))
                        {
                            options.add(m);
                        }
                    }
                }
                textUI.displayMessage("Your recently watched media. ");
                textUI.mediaMenu(options, currentUser, false);
            }

            // search for media
            if (input.equals("6"))
            {
                input = textUI.getUserInput("Search media: ");
                ArrayList<IMedia> options = searchFunction.searchMedia(input);
                textUI.mediaMenu(options, currentUser, false);
            }

            // choose Category
            if(input.equals("7"))
            {
                ArrayList<IMedia> options = searchFunction.viewAllCategory();
                textUI.mediaMenu(options, currentUser, false);
            }

            if (input.equals("8"))
            {
                logOut();
            }
        }
    }

    private void createMedia()
    {
        IO io = new IO();

        ArrayList<String> moviedata = io.readMovieData();
        ArrayList<String> seriesdata = io.readSeriesData();


        for (String s : moviedata)
        {
            String[] values = s.split(";");
            String[] categories = values[2].split(",");
            Movie movie = new Movie(values[0], values[1], categories, values[3], Integer.parseInt(values[4].trim()));
            media.add(movie);

        }

        for (String s : seriesdata)
        {
            String[] values = s.split(";");
            String[] categories = values[2].split(",");
            String[] seasons = values[4].split(",");
            Series series = new Series(values[0], values[1], categories, values[3], seasons, Integer.parseInt(values[5].trim()));
            media.add(series);
        }
    }

    // logout method to return to login screen
    protected void logOut()
    {
        IO io = new IO();
        DatabaseIO databaseIO = new DatabaseIO();
        FileIO fileIO = new FileIO();

        // TODO MABYE DELETE THIS IF STATEMENT, BUT IDK IF IT WILL BREAK ANYTHING

        if(!databaseIO.establishConnection())
        {
            try
            {
                fileIO.deleteFile();
            }
            catch (FileNotFoundException f)
            {
                textUI.displayMessage(f.getMessage());
            }
        }

        io.writeUserData(users, media);

        FedFlix fedFlix = new FedFlix();
        fedFlix.runFedFlix();
    }

}

import java.util.ArrayList;

public class MainMenu
{
    User currentUser;
    ArrayList<IMedia> media = new ArrayList<>();
    public TextUI textUI = new TextUI();

    public MainMenu(User currentUser)
    {
        this.currentUser = currentUser;
    }

    public void runMainMenu()
    {

        createMedia();

        textUI.displayMessage("Welcome to fedFlix, " + currentUser.getUsername() + "!");
        textUI.displayMessage("Press 1 to view all media\npress 2 to view all series\npress 3 to view all movies\n" +
                "press 4 to view your watchlist\npress 5 to view your history\npress 6 to search for media\npress 7 to logout");

        String input = textUI.getUserInput();

        if (input.equals("1"))
        {
            media.toString();
        }

        if (input.equals("2"))
        {
            for (IMedia i : media)
            {
                if (i instanceof Series)
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        if (input.equals("3"))
        {
            for (IMedia i : media)
            {
                if (i instanceof Movie)
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        if (input.equals("4"))
        {

            currentUser.addShowsSeen("The Office");
            currentUser.addShowsSeen("Taxi");
            currentUser.addShowsSeen("The flash");

            currentUser.addFavouriteShows("The Office");
            currentUser.saveUserData(currentUser);
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

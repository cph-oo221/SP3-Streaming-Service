import java.util.ArrayList;

public class SearchFunction
{
   User currentUser;
   ArrayList<IMedia> media;
    public SearchFunction(User currentUser, ArrayList<IMedia> media)
    {
        this.currentUser = currentUser;
        this.media = media;
    }

    //TODO Test searchfunction
    public ArrayList<IMedia> searchMedia(String input)
    {
        ArrayList<IMedia> output = new ArrayList<>();

        for (IMedia m: media)
        {
            if (m.getName().toLowerCase().contains(input.toLowerCase()))
            {
                output.add(m);
            }
        }
        return output;
    }

    // Option 1 view all media
    public void viewAllMedia()
    {
        viewAllSeries();
        viewAllMovies();
    }

    // Option 2 view all series
    public void viewAllSeries()
    {
        for (IMedia i : media)
        {
            if (i instanceof Series)
            {
                textUI.displayMessage(i.toString());
            }
        }
    }

    // Option 3 view all movies
    public void viewAllMovies()
    {
        for (IMedia i : media)
        {
            if (i instanceof Movie)
            {
                textUI.displayMessage(i.toString());
            }
        }
    }

    // Option 4 view watchlist
    public void viewWatchlist()
    {
        for (String s : currentUser.getFavouriteShows())
        {
            textUI.displayMessage(s);
        }
    }

    // Option 5 view history
    public void viewHistory()
    {
        for (String s : currentUser.getShowsSeen())
        {
            textUI.displayMessage(s);
        }
    }


}

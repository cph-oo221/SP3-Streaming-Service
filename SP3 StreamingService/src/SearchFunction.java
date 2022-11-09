import java.util.ArrayList;
import java.util.Arrays;

public class SearchFunction
{
   User currentUser;
   ArrayList<IMedia> media;

   TextUI textUI = new TextUI();
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

    // option viewAllCategory
    public ArrayList<IMedia> viewAllCategory()
    {
        textUI.displayMessage("What category you want to view?");
        textUI.displayMessage("1. Action\n2. Comedy\n3. Drama\n4. Horror\n5. Sci-Fi\n6. Thriller");
        String input = textUI.getUserInput();

        ArrayList<IMedia> output = new ArrayList<>();

        if (input.equals("1"))
        {
            for (IMedia i : media)
            {
                // print all moves and series that contains category Action
                if (i.getGenre().equals("Action"))
                {
                    i.getGenre();
                }
            }
            return output;
        }

        if (input.equals("2"))
        {
            for (IMedia i : media)
            {
                if (i.getGenre().equals("Comedy"))
                {
                    output.add(i);
                }
            }
            return output;
        }

        if (input.equals("3"))
        {
            for (IMedia i : media)
            {
                if (i.getGenre().toString().equals("Drama"))
                {
                    output.add(i);
                }
            }
            return output;
        }

        if (input.equals("4"))
        {
            for (IMedia i : media)
            {
                if (i.getGenre().equals("Horror"))
                {
                    output.add(i);
                }
            }
            return output;
        }

        if (input.equals("5"))
        {
            for (IMedia i : media)
            {
                if (i.getGenre().equals("Sci-Fi"))
                {
                    output.add(i);
                }
            }
            return output;
        }
        return output;
    }
}

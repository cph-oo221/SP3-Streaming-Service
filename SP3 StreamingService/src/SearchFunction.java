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
    public void viewAllCategory()
    {
        textUI.displayMessage("What category would you like to watch?");
        textUI.displayMessage("1. Action\n2. Adventure\n3. Animation\n4. Biography\n5. Comedy\n6. Crime\n7. Documentary\n" +
                "8. Drama\n9. Family\n10. Fantasy\n11. Film-Noir\n12. History\n13. Horror\n14. Music\n15. Musical\n" +
                "16. Mystery\n17. Romance\n18. Sci-fi\n19. Sport\n20. Talk-Show\n21. Thriller\n22. War\n23. Western");
        String input = textUI.getUserInput();

        // View media for Action
        if (input.equals("1"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Action"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Adventure
        if (input.equals("2"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Adventure"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Animation
        if (input.equals("3"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Animation"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Biography
        if (input.equals("4"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Biography"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Comedy
        if (input.equals("5"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Comedy"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Crime
        if (input.equals("6"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Crime"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Documentary
        if (input.equals("7"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Documentary"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Drama
        if (input.equals("8"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Drama"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Family
        if (input.equals("9"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Family"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Fantasy
        if (input.equals("10"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Fantasy"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Film-Noir
        if (input.equals("11"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Film-Noir"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for History
        if (input.equals("12"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("History"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Horror
        if (input.equals("13"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Horror"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Music.
        // !i.toString().contains("Musical") is added to prevent the Musical category from showing up in the Music category.
        if (input.equals("14"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Music") && !i.toString().contains("Musical"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Musical
        if (input.equals("15"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Musical"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Mystery
        if (input.equals("16"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Mystery"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Romance
        if (input.equals("17"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Romance"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Sci-fi
        if (input.equals("18"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Sci-fi"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Sport
        if (input.equals("19"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Sport"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Talk-Show
        if (input.equals("20"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Talk-show"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Thriller
        if (input.equals("21"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Thriller"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for War
        if (input.equals("22"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("War"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }

        // View media for Western
        if (input.equals("23"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Western"))
                {
                    textUI.displayMessage(i.toString());
                }
            }
        }
    }
}

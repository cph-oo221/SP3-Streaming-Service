import java.util.ArrayList;


public class SearchFunction
{
    private User currentUser;
    private ArrayList<IMedia> media;
    private TextUI textUI = new TextUI();

    protected SearchFunction(User currentUser, ArrayList<IMedia> media)
    {
        this.currentUser = currentUser;
        this.media = media;
    }

    protected ArrayList<IMedia> searchMedia(String input)
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
    protected ArrayList<IMedia> viewAllMedia()
    {
        ArrayList<IMedia> allMediaOutput = new ArrayList<>();
        for (IMedia i : media)
        {
            if (i instanceof Series)
            {
                allMediaOutput.add(i);
            }
        }

        for (IMedia i : media)
        {
            if (i instanceof Movie)
            {
                allMediaOutput.add(i);
            }
        }

        return allMediaOutput;
    }

    // Option 2 view all series
    protected ArrayList<IMedia> viewAllSeries()
    {
        ArrayList<IMedia> seriesOutput = new ArrayList<>();

        for (IMedia i : media)
        {
            if (i instanceof Series)
            {
                seriesOutput.add(i);
            }
        }
        return seriesOutput;
    }

    // Option 3 view all movies
    protected ArrayList<IMedia> viewAllMovies()
    {
        ArrayList<IMedia> movieOutput = new ArrayList<>();

        for (IMedia i : media)
        {
            if (i instanceof Movie)
            {
                movieOutput.add(i);
            }
        }
        return movieOutput;
    }

    // option viewAllCategory
    protected ArrayList<IMedia> viewAllCategory()
    {
        ArrayList<IMedia> categoryOutput = new ArrayList<>();

        textUI.displayMessage("What category would you like to watch?");
        textUI.displayMessage("****************************************");
        textUI.displayMessage("""
                1. Action
                2. Adventure
                3. Animation
                4. Biography
                5. Comedy
                6. Crime
                7. Documentary
                8. Drama
                9. Family
                10. Fantasy
                11. Film-Noir
                12. History
                13. Horror
                14. Music
                15. Musical
                16. Mystery
                17. Romance
                18. Sci-fi
                19. Sport
                20. Talk-Show
                21. Thriller
                22. War
                23. Western""");
        textUI.displayMessage("****************************************");
        String input = textUI.getUserInput("Enter a number for your choice: ");

        // View media for Action
        if (input.equals("1"))
        {
            for (IMedia i : media)
            {
                if (i.toString().contains("Action"))
                {
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
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
                    categoryOutput.add(i);
                }
            }
        }

        return categoryOutput;
    }
}

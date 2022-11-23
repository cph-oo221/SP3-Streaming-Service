import java.util.ArrayList;

public class User
{
    // make an arraylist the show the seen movies
    private ArrayList<String> showsSeen = new ArrayList<>();

    // make an arraylist with favouriteShows
    private ArrayList<String> favouriteShows = new ArrayList<>();

    // make a username
    private String username;

    // make a password
    private String password;


    // constructor for a new user.
    protected User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }


    // getters
    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public ArrayList<String> getShowsSeen()
    {
        return showsSeen;
    }

    public ArrayList<String> getFavouriteShows()
    {
        return favouriteShows;
    }

    // setters
    protected void setShowsSeen(ArrayList<String> showsSeen)
    {
        this.showsSeen = showsSeen;
    }

    protected void setFavouriteShows(ArrayList<String> favouriteShows)
    {
        this.favouriteShows = favouriteShows;
    }

    // make a function to add a show to the favouriteShows arraylist

    protected void addFavouriteShows(String show)
    {
        favouriteShows.add(show);
    }

    // make a function to add a show to the showsSeen arraylist
    protected void addShowsSeen(String show)
    {
        this.showsSeen.add(show);
    }

    protected void removeFavouriteShow(String showName)
    {
        favouriteShows.remove(showName);
    }

    @Override
    public String toString()
    {
        return "| Username: " + username + " |Password: " + password +" | Shows seen: " + showsSeen +
                " | Favourite shows: " + favouriteShows + '|';
    }
}
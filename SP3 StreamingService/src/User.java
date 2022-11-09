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

    public FileIO fileIO = new FileIO();


    // constructor for a new user where there is no showsSeen and favouriteShows.
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    // constructor for register user with showsSeen and favouriteShows
    public User(String username, String password, ArrayList<String> showsSeen, ArrayList<String> favouriteShows)
    {
        this.username = username;
        this.password = password;
        this.showsSeen = showsSeen;
        this.favouriteShows = favouriteShows;
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
    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setShowsSeen(ArrayList<String> showsSeen)
    {
        this.showsSeen = showsSeen;
    }

    public void setFavouriteShows(ArrayList<String> favouriteShows)
    {
        this.favouriteShows = favouriteShows;
    }

    // make a function to add a show to the favouriteShows arraylist

    public void addFavouriteShows(String show)
    {
        favouriteShows.add(show);
    }

    // make a function to add a show to the showsSeen arraylist
    public void addShowsSeen(String show)
    {
        this.showsSeen.add(show);
    }

    // Make a function that saves showsSeen and favouriteShows to a file
    public void saveUserData(User user)
    {
        fileIO.writeUserData(user, showsSeen, favouriteShows);
    }

    @Override
    public String toString()
    {
        return "| Username: " + username + " | Shows seen: " + showsSeen +
                " | Favourite shows: " + favouriteShows + '|';
    }
}
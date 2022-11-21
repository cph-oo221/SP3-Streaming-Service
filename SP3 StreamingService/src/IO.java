import java.util.ArrayList;

public class IO
{
    private DatabaseIO databaseIO = new DatabaseIO();
    private FileIO fileIO = new FileIO();
    private TextUI textUI = new TextUI();

    protected ArrayList<String> readMovieData()
    {
        textUI.displayMessage("Attempting to connect to database...");
        if (isConnected())
        {
            textUI.displayMessage("Connection succesful");
            return databaseIO.readMovieData();
        }
        else
        {
            textUI.displayMessage("Connection failed. using internal storage");
            return fileIO.readMovieData();
        }
    }
    public boolean isConnected ()
    {
        return databaseIO.establishConnection();
    }

    protected ArrayList<String> readSeriesData()
    {
        textUI.displayMessage("Attempting to connect to database...");
        if (isConnected())
        {
            textUI.displayMessage("Connection succesful");
            return databaseIO.readSeriesData();
        }
        else
        {
            textUI.displayMessage("Connection failed. using internal storage");
            return fileIO.readSeriesData();
        }
    }

    protected ArrayList<String> readUserData()
    {
        textUI.displayMessage("Attempting to connect to database...");
        if (isConnected())
        {
            textUI.displayMessage("Connection succesful");
            return databaseIO.readUserData();
        }
        else
        {
            textUI.displayMessage("Connection failed. using internal storage");
            return fileIO.readUserData();
        }
    }

    protected void writeUserData(ArrayList<User> users)
    {
        textUI.displayMessage("Attempting to connect to database...");
        if (isConnected())
        {
            textUI.displayMessage("Connection succesful");
            databaseIO.writeUserData(users);
        }
        else
        {
            textUI.displayMessage("Connection failed. using internal storage");
            fileIO.writeUserData(users);
        }
    }
}



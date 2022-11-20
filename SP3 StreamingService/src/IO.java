import java.util.ArrayList;

public class IO
{
    private DatabaseIO databaseIO = new DatabaseIO();
    private FileIO fileIO = new FileIO();

    private TextUI textUI = new TextUI();

    protected ArrayList<String> readMovieData()
    {
        if (databaseIO.establishConnection())
        {
            return databaseIO.readMovieData();
        }
        else
        {
            textUI.displayMessage("There is no connection to database, using internal storage");
            return fileIO.readMovieData();
        }
    }

    protected ArrayList<String> readSeriesData()
    {
        if (databaseIO.establishConnection())
        {
            return databaseIO.readSeriesData();
        }
        else
        {
            return fileIO.readSeriesData();
        }
    }

    protected ArrayList<String> readUserData()
    {
        textUI.displayMessage("Attempting to connect to database...");
        if (databaseIO.establishConnection())
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
        if (databaseIO.establishConnection())
        {
            databaseIO.writeUserData(null);
        }
        else
        {
            fileIO.writeUserData(null);
        }
    }

    protected int userCounter()
    {
        return 1;
    }

}


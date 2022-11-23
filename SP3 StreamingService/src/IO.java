import java.util.ArrayList;

public class IO
{
    private DatabaseIO databaseIO = new DatabaseIO();
    private FileIO fileIO = new FileIO();

    private TextUI textUI = new TextUI();

    private boolean isConnected = true;

    protected ArrayList<String> readMovieData()
    {

        if (isConnected)
        {

            return databaseIO.readMovieData();
        }
        else
        {

            return fileIO.readMovieData();
        }
    }

    protected ArrayList<String> readSeriesData()
    {

        if (isConnected)
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

        isConnected = databaseIO.establishConnection();

        if (isConnected)
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

    protected void writeUserData(ArrayList<User> users, ArrayList<IMedia> media)
    {

        if (isConnected)
        {

            databaseIO.writeUserData(users, media);
        }
        else
        {

            fileIO.writeUserData(users);
        }
    }
}



import java.util.ArrayList;

public class IO
{
    private DatabaseIO databaseIO = new DatabaseIO();
    private FileIO fileIO = new FileIO();

    protected ArrayList<String> readMovieData() // protected
    {
        if (databaseIO.establishConnection())
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
        if (databaseIO.establishConnection())
        {
            return databaseIO.readSeriesData();
        }
        else
        {
            return fileIO.readSeriesData();
        }
    }

    public ArrayList<String> readUserData()
    {
        if (databaseIO.establishConnection())
        {
            return databaseIO.readUserData();
        }
        else
        {
            return fileIO.readUserData();
        }
    }

    protected void writeUserData(ArrayList<User> users)
    {
        if (databaseIO.establishConnection())
        {
            // return databaseIO.writeUserData();
        }
        else
        {
            // return fileIO.writeUserData(null);
        }
    }

    protected int userCounter()
    {
        return 1;
    }

}



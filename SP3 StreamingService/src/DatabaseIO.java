import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseIO
{
    private Connection connection;
    private String url = "jdbc:mysql://localhost/fedflixdb?" + "autoReconnect=true&useSSL=false";
    private String username ="root";
    private String password ="oo123";

    public boolean establishConnection()
    {
        // CONNECTION
        try
        {
            connection = DriverManager.getConnection(url, username, password);
            return connection.isValid(1);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> readMovieData()
    {
        return null;
    }

    public ArrayList<String> readSeriesData()
    {
        return null;
    }

    public ArrayList<String> readUserData()
    {
        return null;
    }


    public void writeUserData(ArrayList<User> users)
    {

    }
}

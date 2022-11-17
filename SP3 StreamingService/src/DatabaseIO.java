import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseIO
{
    private Connection connection;
    private String url = "jdbc:mysql://localhost/fedflixdb?" + "autoReconnect=true&useSSL=false";
    private String username ="root";
    private String password ="oo123";

    public void establishConnection()
    {
        // CONNECTION
        try
        {
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}

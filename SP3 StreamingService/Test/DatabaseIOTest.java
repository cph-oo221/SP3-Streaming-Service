

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class DatabaseIOTest
{
    Connection connection;

    String url = "jdbc:mysql://localhost/fedFlixDB?" + "autoReconnect=true&useSSL=false";
    String username = "kotteletfisk";
    String password = "sovs";

    @Test
    public void establishConnection()
    {
        try
        {
            connection = DriverManager.getConnection(url, username, password);
            assertEquals(true, connection.isValid(1));
        }


        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void retrieveDataFromDataBase()
    {
        establishConnection();

        String all_movies_query = "SELECT * FROM movieList;";

        try
        { Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(all_movies_query);


            while (result.next())
            {
                String name = result.getString("Name");
                String year = result.getString("Year");
                String categories = result.getString("Categories");
                String rating = result.getString("Rating");

                System.out.println(name + " " + year + " " + categories + " " + rating);
            }

        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

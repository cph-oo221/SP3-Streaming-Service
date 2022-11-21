import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

public class ReadUserDataTest
{
    private Connection connection;
    private String url = "jdbc:mysql://localhost/fedflixdb?" + "autoReconnect=true&useSSL=false";
    private String username ="root";
    private String password ="abc123";
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
    @Test
    public void readUserData()
    {
        establishConnection();

        ArrayList<String> data = new ArrayList<>();

        try
        {
            String users_ID_query = "SELECT name, password, user_id FROM userdata;";

            Statement statement = connection.createStatement();

            ResultSet userResult = statement.executeQuery(users_ID_query);

            ArrayList<Integer> IDs = new ArrayList<Integer>();
            while (userResult.next())
            {
                int ID = userResult.getInt("user_id");
                IDs.add(ID);
            }
            for(Integer ID : IDs)
            {



                /*
                String name = userResult.getString("name");
                String password = userResult.getString("password");
                int user_id = userResult.getInt("user_id");
                userResult.close();


                // Watchlist
                String users_watchlist_query = "SELECT movie_id, name, series_id, user_id FROM watchlists;";
                String watchlist = "";
                ResultSet watchlistResult = statement.executeQuery(users_watchlist_query);
                int user_id_watchlist = watchlistResult.getInt("user_id");



                //showsSeen
                String users_showsSeen_query = "SELECT movie_id, series_id, user_id FROM showsseen;";
                String showsSeen = "";
                ResultSet showsSeenResult = statement.executeQuery(users_showsSeen_query);
                int user_id_showsseen = showsSeenResult.getInt("user_id");


                String userDataStr = (name + "," + password + ";" + showsSeen + ";" + watchlist + ";");
                System.out.println(userDataStr);
                data.add(userDataStr);
                */

            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

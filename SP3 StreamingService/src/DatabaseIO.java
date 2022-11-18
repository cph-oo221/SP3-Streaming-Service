import java.sql.*;
import java.util.ArrayList;

public class DatabaseIO
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
        establishConnection();

        ArrayList<String> data = new ArrayList<>();

            try
            {
                String users_ID_query = "SELECT name, password FROM userdata;";

                Statement statement = connection.createStatement();

                ResultSet userResult = statement.executeQuery(users_ID_query);
                //Read the user's watchlist
                //Compare watchlist.user_id and userdata.user_id
                String users_watchlist_query = "SELECT movie_id, series_id FROM watchlists";

                //Read the user's showsSeen
                //Compare showsseen.user_id and userdata.user_id
                String users_showsSeen_query = "SELECT movie_id, series_id FROM showsseen";
                //Send the queries and recieve resultsets
                ResultSet watchlistResult = statement.executeQuery(users_watchlist_query);
                ResultSet showsSeenResult = statement.executeQuery(users_showsSeen_query);

                while (userResult.next())
                {

                    String name = userResult.getString("name");
                    String password = userResult.getString("password");
                    String watchlist = "";
                    int user_id = userResult.getInt("user_id");
                    int user_id_watchlist = watchlistResult.getInt("user_id");
                    int user_id_showsseen = showsSeenResult.getInt("user_id");

                    if(user_id == user_id_watchlist)
                    {
                        watchlist = watchlistResult.getString("name"+",");
                    }
                    String showsSeen = "";
                    if(user_id == user_id_showsseen)
                    {
                        showsSeen = showsSeenResult.getString("name"+",");
                    }
                    //Create the string for watchlist

                    String userDataStr = (name + "," + password + ";" + showsSeen + ";" + watchlist + ";");
                    data.add(userDataStr);
                }
            }

            catch (SQLException e)
            {
                e.printStackTrace();
            }
        return data;
        }



    public void writeUserData(ArrayList<User> users)
    {

    }
}

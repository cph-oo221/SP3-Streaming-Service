import java.sql.*;
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
            return false;
        }
    }

    public ArrayList<String> readMovieData()
    {
        // establish connection
        establishConnection();

        // Make a list to store the data
        ArrayList<String> movieData = new ArrayList<>();

        // Statement select all movies
        String query = "SELECT * FROM movielist";

        try
        {
            // create statement
            Statement statement = connection.createStatement();

            // execute statement
            statement.executeQuery(query);

            // get resultset
            ResultSet resultSet = statement.getResultSet();

            // while there is a next row
            while (resultSet.next())
            {
                // get the data from the row
                String movieName = resultSet.getString("Name");
                String movieYear = resultSet.getString("Year");
                String movieCategories = resultSet.getString("Categories");
                String movieRating = resultSet.getString("Rating");
                int id = resultSet.getInt("movie_id");

                // make string k that takes the data from the row
                String k = movieName + ";" + movieYear + ";" + movieCategories + ";" + movieRating + ";" + id + ";";

                // add k to arraylist
                movieData.add(k);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return movieData;
    }

    public ArrayList<String> readSeriesData()
    {
        // establish connection
        establishConnection();

        // Make a list to store the data
        ArrayList<String> seriesData = new ArrayList<>();

        // Statement select all series
        String query = "SELECT * FROM serieslist";

        try
        {
            // create statement
            Statement statement = connection.createStatement();

            // execute statement
            statement.executeQuery(query);

            // get resultset
            ResultSet resultSet = statement.getResultSet();

            // while there is a next row
            while (resultSet.next())
            {
                // get data from row
                String seriesName = resultSet.getString("Name");
                String seriesYear = resultSet.getString("Year");
                String seriesCategories = resultSet.getString("Categories");
                String seriesRating = resultSet.getString("Rating");
                String seriesSeasons = resultSet.getString("Seasons");
                int id = resultSet.getInt("series_id");

                String k = seriesName + ";" + seriesYear + ";" + seriesCategories + ";" + seriesRating + ";" + seriesSeasons + ";" + id + ";";

                // add k to arraylist
                seriesData.add(k);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return seriesData;
    }

    public ArrayList<String> readUserData()
    {
        establishConnection();

        ArrayList<String> output = new ArrayList<>();

        // read users from database
        String user_data_query = "SELECT * FROM userdata;";

        try
        {
            Statement statement = connection.createStatement();

            statement.executeQuery(user_data_query);

            ResultSet user_result = statement.getResultSet();

            // get string for every user
            while (user_result.next())
            {
                StringBuilder concat_string = new StringBuilder();

                Statement inner_statement = connection.createStatement();

                int id = user_result.getInt("user_id");

                String name = user_result.getString("Name");

                String pass = user_result.getString("Password");

                concat_string.append(name + "," + pass + ";");


                // get showsseen list names. Concat to userstring
                String showsseen_query = "SELECT movielist.Name moviename, serieslist.Name seriesname FROM showsseen\n" +
                        "LEFT JOIN movielist ON movielist.movie_id = showsseen.media_id\n" +
                        "LEFT JOIN serieslist ON serieslist.series_id = showsseen.media_id \n" +
                        "WHERE user_id = " + id + ";";


                inner_statement.executeQuery(showsseen_query);

                ResultSet showsSeen_result = inner_statement.getResultSet();


                if (showsSeen_result.next())
                {
                    do
                    {
                        if (showsSeen_result.getString("moviename") != null)
                        {
                            concat_string.append(showsSeen_result.getString("moviename") + ",");
                        }

                        if (showsSeen_result.getString("seriesname") != null)
                        {
                            concat_string.append(showsSeen_result.getString("seriesname") + ",");
                        }

                    } while (showsSeen_result.next());
                }

                else concat_string.append("null");

                concat_string.append(";");


                // get watchlist names. Concat to userstring
                String watchlists_query ="SELECT movielist.Name moviename, serieslist.Name seriesname FROM watchlists\n" +
                        "LEFT JOIN movielist ON movielist.movie_id = watchlists.media_id\n" +
                        "LEFT JOIN serieslist ON serieslist.series_id = watchlists.media_id \n" +
                        "WHERE user_id = " + id + ";";

                inner_statement.executeQuery(watchlists_query);

                ResultSet watchlists_result = inner_statement.getResultSet();


                if (watchlists_result.next())
                {
                    do
                    {
                        if (watchlists_result.getString("moviename") != null)
                        {
                            concat_string.append(watchlists_result.getString("moviename") + ",");
                        }

                        if (watchlists_result.getString("seriesname") != null)
                        {
                            concat_string.append(watchlists_result.getString("seriesname") + ",");
                        }

                    } while (watchlists_result.next());
                }

                else concat_string.append("null");

                concat_string.append(";");

                inner_statement.close();

                // add concatinated user string to arraylist
                output.add(concat_string.toString());
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return output;
    }


    public void writeUserData(ArrayList<User> users, ArrayList<IMedia> media) // users arraylist parameter
    {

        for (User user : users)
        {
            ArrayList<Integer> showseen_id = new ArrayList<>();
            ArrayList<Integer> watchlist_id = new ArrayList<>();

            // translate user showseen to media id
            for (String showseen_media_name : user.getShowsSeen())
            {
                for (IMedia m : media)
                {
                    if (m.getName().equals(showseen_media_name))
                    {
                        showseen_id.add(m.getId());
                    }
                }
            }
            // translate user watchlist to media id
            for (String watchlist_media_name : user.getFavouriteShows())
            {
                for (IMedia m : media)
                {
                    if (m.getName().equals(watchlist_media_name))
                    {
                        watchlist_id.add(m.getId());
                    }
                }
            }

            try
            {
                // establish connection
                establishConnection();

                // get users from database
                String get_user_query = "SELECT user_id FROM userdata WHERE Name LIKE '" + user.getUsername() + "';";

                Statement statement = connection.createStatement();

                ResultSet db_user = statement.executeQuery(get_user_query);

                Statement inner_statement = connection.createStatement();

                if (db_user.next())
                {
                    // Update user lists if user exists

                    int user_id = db_user.getInt("user_id");

                    String delete_current_db_showsseen = "DELETE FROM showsseen WHERE user_id = " + user_id + ";";
                    String delete_current_db_watchlist = "DELETE FROM watchlists WHERE user_id = " + user_id + ";";

                    inner_statement.execute(delete_current_db_showsseen);
                    inner_statement.execute(delete_current_db_watchlist);

                    if (showseen_id.size() > 0)
                    {
                        for (int media_id : showseen_id)
                        {
                            inner_statement.execute("INSERT INTO showsseen (user_id, media_id) VALUES (" + user_id + ", " + media_id + ");");
                        }
                    }

                    if (watchlist_id.size() > 0)
                    {
                        for (int media_id : watchlist_id)
                        {
                            inner_statement.execute("INSERT INTO watchlists (user_id, media_id) VALUES (" + user_id + ", " + media_id + ");");
                        }
                    }
                }

                else
                {
                    // Insert new user if user does not exist
                    String insert_user_query = "INSERT INTO userdata (Name, Password) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "');";

                    // get user id
                    inner_statement.execute(insert_user_query);

                    // Get user id from userdata table
                    ResultSet user_id_result = inner_statement.executeQuery(get_user_query);
                    user_id_result.next();
                    int user_id = user_id_result.getInt("user_id");

                    // Insert showsseen
                    if (showseen_id.size() > 0)
                    {
                        for (int media_id : showseen_id)
                        {
                            inner_statement.execute("INSERT INTO showsseen (user_id, media_id) VALUES (" + user_id + ", " + media_id + ");");
                        }
                    }

                    // Insert watchlists
                    if (watchlist_id.size() > 0)
                    {
                        for (int media_id : watchlist_id)
                        {
                            inner_statement.execute("INSERT INTO watchlists (user_id, media_id) VALUES (" + user_id + ", " + media_id + ");");
                        }
                    }
                }
            }

            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
    }
}



import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DatabaseIOTest
{
    Connection connection;

    String url = "jdbc:mysql://localhost/fedflixdb?" + "autoReconnect=true&useSSL=false";
    String username = "kotteletfisk";
    String password = "sovs";

    @Test
    public void establishConnection()
    {
        try
        {
            connection = DriverManager.getConnection(url, username, password);
            assertTrue(connection.isValid(1));
        }


        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void readMovieDataTest()
    {
        establishConnection();

        ArrayList<String> output = new ArrayList<>();
        String all_movies_query = "SELECT * FROM movielist;";

        try
        { Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(all_movies_query);


            while (result.next())
            {
                String name = result.getString("Name");
                String year = result.getString("Year");
                String categories = result.getString("Categories");
                String rating = result.getString("Rating");
                int id = result.getInt("movie_id");

                // System.out.println(name + " " + year + " " + categories + " " + rating);

                String concat = name.trim() + ";" + year.trim()  + ";" + categories.trim()  + ";" + rating.trim() + ";" + id + ";";

                output.add(concat);
            }

            for (String s: output)
            {
                System.out.println(s);
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }
    @Test
    public void readUserDataTest() // return arraylist<String>
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

            while (user_result.next())
            {
                StringBuilder concat_string = new StringBuilder();

                Statement inner_statement = connection.createStatement();

                int id = user_result.getInt("user_id");

                String name = user_result.getString("Name");

                String pass = user_result.getString("Password");

                concat_string.append(name + "," + pass + ";");


                // get showseen names list. concat to userstring
                String showsseen_query = "SELECT movielist.Name moviename, serieslist.Name seriesname FROM showsseen\n" +
                        "LEFT JOIN movielist ON movielist.movie_id = showsseen.media_id\n" +
                        "LEFT JOIN serieslist ON serieslist.series_id = showsseen.media_id\n" +
                        "WHERE user_id = " + id + ";";


                inner_statement.executeQuery(showsseen_query);

                ResultSet showsSeen_result = inner_statement.getResultSet();


                if (showsSeen_result.next())
                {
                    //showsSeen_result.beforeFirst();
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

                    }   while (showsSeen_result.next());
                }

                else concat_string.append("null");

                concat_string.append(";");


                // get watchlist. concat to userstring
                String watchlists_query ="SELECT movielist.Name moviename, serieslist.Name seriesname FROM watchlists\n" +
                        "LEFT JOIN movielist ON movielist.movie_id = watchlists.media_id\n" +
                        "LEFT JOIN serieslist ON serieslist.series_id = watchlists.media_id\n" +
                        "WHERE user_id = " + id + ";";

                inner_statement.executeQuery(watchlists_query);

                ResultSet watchlists_result = inner_statement.getResultSet();


                if (watchlists_result.next())
                {
                    //watchlists_result.beforeFirst();
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
            for (String s: output)
            {
                System.out.println(s);
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void writeUserDataTest() // users arraylist parameter
    {
        ArrayList<Media> media = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

            for (User user : users)
            {
                ArrayList<Integer> showseen_id = new ArrayList<>();
                ArrayList<Integer> watchlist_id = new ArrayList<>();

                // translate user showseen to media id
                for (String showseen_media_name : user.getShowsSeen())
                {
                    for (Media m : media)
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
                    for (Media m : media)
                    {
                        if (m.getName().equals(watchlist_media_name))
                        {
                            watchlist_id.add(m.getId());
                        }
                    }
                }

                try
                {
                    // establi---sh connection
                    establishConnection();

                    // get users from database
                    String get_user_query = "SELECT user_id FROM userdata WHERE Name = " + user.getUsername() + ";";

                    Statement statement = connection.createStatement();

                    ResultSet db_user = statement.executeQuery(get_user_query);

                    Statement inner_statement = connection.createStatement();

                    if (db_user.next())
                    {
                        // Update user lists if user exists

                        int user_id = db_user.getInt("user_id");

                        String delete_current_db_showsseen = "DELETE FROM showseen WHERE user_id = " + user_id + ";";

                        inner_statement.execute(delete_current_db_showsseen);

                        if (showseen_id.size() > 0)
                        {
                            for (int media_id : showseen_id)
                            {
                                inner_statement.execute("INSERT INTO showseen (user_id, media_id) VALUES (" + user_id + ", " + media_id + ");");
                            }
                        }

                        if (watchlist_id.size() > 0)
                        {
                            for (int media_id : watchlist_id)
                            {
                                inner_statement.execute("INSER INTO watchlists (user_id, media_id) VALUES (" + user_id + ", " + media_id + ");");
                            }
                        }
                    }

                    else
                    {
                        inner_statement.execute("INSERT INTO userdata (Name, Password) VALUES (" + user.getUsername() + ", " + user.getPassword() + ");");
                        ResultSet user_id = inner_statement.executeQuery("SELECT user_id FROM userdata WHERE Name = " + user.getUsername() + ";");

                        if (showseen_id.size() > 0)
                        {
                            for (int media_id: showseen_id)
                            {
                                inner_statement.execute("INSERT INTO showsseen (" + user_id + ", " + media_id + ");");
                            }

                        }

                        if (watchlist_id.size() > 0)
                        {
                            for (int media_id: watchlist_id)
                            {
                                inner_statement.execute("INSERT INTO watchlists (" + user_id + ", " + media_id + ");");
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


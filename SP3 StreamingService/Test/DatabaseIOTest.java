

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
                        "LEFT JOIN movielist ON movielist.movie_id = showsseen.movie_id\n" +
                        "LEFT JOIN serieslist ON serieslist.series_id = showsseen.series_id \n" +
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
                        "LEFT JOIN movielist ON movielist.movie_id = watchlists.movie_id\n" +
                        "LEFT JOIN serieslist ON serieslist.series_id = watchlists.series_id \n" +
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

//    @Test
//    public void writeUserDataTest(ArrayList<User> users)
//    {
//        // establi---sh connection
//        establishConnection();
//
//        String get_usernames_query = "SELECT user_id, Name FROM userdata;";
//
//        try
//        {
//            Statement statement = connection.createStatement();
//
//            ResultSet usernames = statement.executeQuery(get_usernames_query);
//
//            while (usernames.next())
//            {
//                int id;
//
//                String write_user_query = "";
//
//                for (User u: users)
//                {
//                    if (u.getUsername().equals(usernames.getString("Name")))
//                    {
//                        // user already exists. update showsseen and watchlist.
//
//                        id = usernames.getInt("user_id");
//
//                        // search for showseen media id's for user.
//
//                        //
//                    }
//                }
//            }
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
}

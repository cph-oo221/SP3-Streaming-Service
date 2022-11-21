import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseIO
{
    private Connection connection;
    private String url = "jdbc:mysql://localhost/fedflixdb?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "abc123";

    public boolean establishConnection()
    {
        // CONNECTION
        try
        {
            connection = DriverManager.getConnection(url, username, password);
            return connection.isValid(1);
        } catch (SQLException e)
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


                // make string k that takes the data from the row
                String k = movieName + ";" + movieYear + ";" + movieCategories + ";" + movieRating;

                // add k to arraylist
                movieData.add(k);
            }
        } catch (SQLException e)
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


                String k = seriesName + ";" + seriesYear + ";" + seriesCategories + ";" + seriesRating + ";" + seriesSeasons;

                // add k to arraylist
                seriesData.add(k);
            }
        } catch (SQLException e)
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
                        "LEFT JOIN movielist ON movielist.movie_id = showsseen.movie_id\n" +
                        "LEFT JOIN serieslist ON serieslist.series_id = showsseen.series_id \n" +
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
                } else concat_string.append("null");

                concat_string.append(";");


                // get watchlist names. Concat to userstring
                String watchlists_query = "SELECT movielist.Name moviename, serieslist.Name seriesname FROM watchlists\n" +
                        "LEFT JOIN movielist ON movielist.movie_id = watchlists.movie_id\n" +
                        "LEFT JOIN serieslist ON serieslist.series_id = watchlists.series_id \n" +
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
                } else concat_string.append("null");

                concat_string.append(";");

                inner_statement.close();

                // add concatinated user string to arraylist
                output.add(concat_string.toString());
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return output;
    }


    public void writeUserData(ArrayList<User> users)
    {
        // establish connection
        establishConnection();

        // Statement writeUserDate

        // join showsseen on showsseen.user_id = userdata.user_id join watchlists on watchlists.user_id = userdata.user_id;
        String wrtie_User_query = "INSERT INTO userdata (Name, Password) VALUES (?, ?)";

        try
        {
            // create statement
            PreparedStatement preparedStatement = connection.prepareStatement(wrtie_User_query);

            // for every user in users
            for (User user : users)
            {
                String username_exists_query = "SELECT name FROM userdata;";
                Statement inner_statement = connection.createStatement();
                inner_statement.executeQuery(username_exists_query);
                ResultSet resultSet = inner_statement.getResultSet();
                ArrayList<String> names = new ArrayList<>();
                while (resultSet.next())
                {
                    names.add(resultSet.getString("name"));
                }
                // only write users that are not already in database
                //;null;null is the default value for a user that is not in the database yet and has no showsseen or watchlist

                if (!names.contains(user.getUsername())) //  || (!readUserData().contains(user.getUsername() + "," + user.getPassword() + user.getShowsSeen() + user.getFavouriteShows()))
                {
                    // set values
                    preparedStatement.setString(1, user.getUsername());
                    preparedStatement.setString(2, user.getPassword());

                    // execute statement
                    preparedStatement.execute();
                }
            }

            // close statement
            preparedStatement.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void deleteMediaFromShowsSeen(User currentUser)
    {
        establishConnection();
        String getUserdata_query = "SELECT * FROM userdata;";
        Statement statement = null;
        int id = 0;
        try
        {
            statement = connection.createStatement();
            statement.executeQuery(getUserdata_query);

            ResultSet userdata = statement.getResultSet();
            while (userdata.next())
            {
                if (currentUser.getUsername() == userdata.getString("name"))
                {
                    id = userdata.getInt("user_id");
                    break;
                }
            }
            statement.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        ArrayList<String> showsSeen = currentUser.getShowsSeen();
        ArrayList<String> showsSeenMatch = new ArrayList<>();
        String mediaList_query = "SELECT movielist.Name moviename, serieslist.Name seriesname FROM showsseen\n" +
                "LEFT JOIN movielist ON movielist.movie_id = showsseen.movie_id\n" +
                "LEFT JOIN serieslist ON serieslist.series_id = showsseen.series_id \n";
        try
        {
            statement = connection.createStatement();

            statement.executeQuery(mediaList_query);

            ResultSet allMedia_result = statement.getResultSet();

            ArrayList<String> allMedia = new ArrayList<>();

            while (allMedia_result.next())
            {
                for (String s : showsSeen)
                {
                    if(s.equals(allMedia_result.getString("Name")))
                    {
                        showsSeenMatch.add(allMedia_result.getString("series_id"));
                    }
                }
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }


        String showsseen_query = "SELECT movielist.Name moviename, serieslist.Name seriesname FROM showsseen\n" +
                "LEFT JOIN movielist ON movielist.movie_id = showsseen.movie_id\n" +
                "LEFT JOIN serieslist ON serieslist.series_id = showsseen.series_id \n" +
                "WHERE user_id = " + id + ";";
        for (String s : showsSeen)
        {
            String [] mediaData = s.split(";");
            try
            {
                statement = connection.createStatement();

                statement.executeQuery(showsseen_query);

                ResultSet showsSeen_result = statement.getResultSet();


                while (showsSeen_result.next())
                {
                    if (!currentUser.getShowsSeen().contains(showsSeen_result.getString("name")))
                    {
                        int mediaID = Integer.parseInt(mediaData[0]);
                        if(mediaID <= 100)
                        {
                            String addToSeen_Query = "INSERT INTO fedflixdb.showsseen (movie_id, user_id) VALUES (?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(addToSeen_Query);
                            preparedStatement.setInt(1, (mediaID));
                            preparedStatement.setInt(2, (id));
                            preparedStatement.execute();
                        }
                        else
                        {
                            String addToSeen_Query = "INSERT INTO fedflixdb.showsseen (series_id, user_id) VALUES (?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(addToSeen_Query);
                            preparedStatement.setInt(1, (mediaID));
                            preparedStatement.setInt(2, (id));
                            preparedStatement.execute();
                        }
                    }
                }
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}

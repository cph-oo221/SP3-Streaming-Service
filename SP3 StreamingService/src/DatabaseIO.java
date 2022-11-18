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
            System.out.println("Connection established successfully"); // TODO <- remove this at some point
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


                // make string k that takes the data from the row
                String k = movieName + ";" + movieYear + ";" + movieCategories + ";" + movieRating;

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


                String k = seriesName + ";" + seriesYear + ";" + seriesCategories + ";" + seriesRating + ";" + seriesSeasons;

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
       return null;
    }


    public void writeUserData(ArrayList<User> users)
    {

    }
}

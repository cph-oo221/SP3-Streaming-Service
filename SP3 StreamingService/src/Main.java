import java.util.ArrayList;

public class Main
{
    private static FedFlix fedFlix = new FedFlix();
    public static void main(String[] args)
    {
        // test if readMovieData works
        DatabaseIO databaseIO = new DatabaseIO();
        ArrayList<String> movieData = databaseIO.readMovieData();
        for (String s : movieData)
        {
            System.out.println(s);
        }

        // test if readSeriesData works
        /*DatabaseIO databaseIO = new DatabaseIO();
        ArrayList<String> seriesData = databaseIO.readSeriesData();
        for (String s : seriesData)
        {
            System.out.println(s);
        }*/


        fedFlix.runFedFlix();
    }
}
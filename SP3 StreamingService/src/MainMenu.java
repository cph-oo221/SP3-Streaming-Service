import java.util.ArrayList;

public class MainMenu
{
    User currentUser;
    ArrayList<IMedia> media = new ArrayList<>();

    SearchFunction search = new SearchFunction(currentUser, media);

    public MainMenu(User currentUser)
    {
        this.currentUser = currentUser;
    }

    public void runMainMenu()
    {
        createMedia();


    }

    private void createMedia()
    {
        FileIO fileIO = new FileIO();
        ArrayList<String> moviedata = new ArrayList<>();
        ArrayList<String> seriesdata = new ArrayList<>();

        seriesdata = fileIO.readSeriesData();
        moviedata = fileIO.readMovieData();

        for (String s : moviedata)
        {
            String[] values = s.split(";");
            String[] categories = values[2].split(",");
            Movie movie = new Movie(values[0], values[1], categories, values[3]);
            media.add(movie);
        }

        for (String s : seriesdata)
        {
            String[] values = s.split(";");
            String[] categories = values[2].split(",");
            String[] seasons = values[4].split(",");
            Series series = new Series(values[0], values[1], categories, values[3], seasons);
            media.add(series);
        }


    }

}

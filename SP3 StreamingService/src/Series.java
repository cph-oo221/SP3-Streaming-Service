import java.util.Arrays;

public class Series extends Media
{
    private String[] seasons;

    private final String type = "Series";


    public Series(String name, String year, String[] genre, String rating, String[] seasons, int id)
    {
        super(name, year, genre, rating, id);
        this.seasons = seasons;
    }

    public String toString()
    {
        return super.toString() + ", " + "Type: " +  type + ", Seasons: " + Arrays.toString(seasons) + '\n';
    }
}
import java.util.Arrays;

public class Series extends Media
{
    String[] seasons;

    String type = "Series";


    public Series(String name, String year, String[] genre, String rating, String[] seasons)
    {
        super(name, year, genre, rating);
        this.seasons = seasons;
    }

    public String toString()
    {
        return super.toString() + ", " + "Type: " +  type + ", Seasons: " + Arrays.toString(seasons) + '\n';
    }
}
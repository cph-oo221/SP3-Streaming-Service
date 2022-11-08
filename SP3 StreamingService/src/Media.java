import java.util.Arrays;

abstract public class Media implements IMedia
{
    private String name;

    private String year; //Proper datatypes, or just strings???

    private String[] genre;

    private String rating;

    public Media(String name, String year, String[] genre, String rating)
    {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }
    public void play()
    {
        System.out.println("Now Playing " + name +"...");
    }

    @Override
    public String toString()
    {
        return "Media | " +
                "Name: " + name +
                ", Year: " + year +
                ", Genre: " + Arrays.toString(genre) +
                ", Rating: " + rating;
    }

    public String getName()
    {
        return name;
    }

    public String getYear()
    {
        return year;
    }

    public String[] getGenre()
    {
        return genre;
    }

    public String getRating()
    {
        return rating;
    }
}
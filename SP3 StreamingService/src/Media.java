import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

abstract public class Media implements IMedia
{
    private String name;

    private String year;

    private String[] genre;

    private String rating;

    private int id;

    public Media(String name, String year, String[] genre, String rating, int id)
    {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.id = id;
    }
    public void play() throws URISyntaxException, IOException
    {

        TextUI text = new TextUI();

        text.displayMessage("You are now watching " + name +" and you are having a jolly good time!");
        text.displayMessage("Press any key to return to menu.");


        //RickRoll happens to user
        Desktop d = Desktop.getDesktop();

        // new version https://streamja.com/lemyz
        // old version https://shattereddisk.github.io/rickroll/rickroll.mp4
        d.browse(new URI("https://streamja.com/lemyz"));
    }

    @Override
    public String toString()
    {
        return "Name: " + name +
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

    public int getId()
    {
        return id;
    }
}
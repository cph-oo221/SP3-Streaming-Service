import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Scanner;

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
    public void play() throws URISyntaxException, IOException
    {

        TextUI text = new TextUI();

        Scanner scan = new Scanner(System.in);
        String input = "";



        text.displayMessage("You are now watching " + name +" and you are having a jolly good time!");


        //RickRoll happens to user
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://shattereddisk.github.io/rickroll/rickroll.mp4%22" ));

                //User can exit to main menu.
                text.displayMessage("Would you like to exit to the Main Menu");
        text.displayMessage("If yes press 1, otherwise just enjoy your movie");

        input = String.valueOf(scan.hasNextLine());
        boolean playing = true;
        while(playing)
        {
            if (input.equals("1"))
            {
                playing = false;
            }
        }
        scan.close();
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO
{

    public ArrayList<String> readMovieData()
    {
        File file = new File("Data/movieList.csv");
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                data.add(input.nextLine() + "\n");
            }
        }
        catch (FileNotFoundException e)
        {
            data = null;
        }
        return data;
    }

    public ArrayList<String> readSeriesData()
    {
        File file = new File("Data/seriesList.csv");
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                data.add(input.nextLine() + "\n");
            }
        }
        catch (FileNotFoundException e)
        {
            data = null;
        }
        return data;
    }



    public ArrayList<String> readUserData()
    {
        File file = new File("Data/userData.csv");
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                data.add(input.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            data = null;
        }
        return data;
    }

    public void writeUserData(User user)
    {
        try
        {
            Scanner input = new Scanner(System.in);
            FileWriter writer = new FileWriter("Data/userData.csv", true);

            writer.write(user.getUsername() + ", ");
            writer.write(user.getPassword() + "; ");

            writer.close();

        }
        catch (IOException e)
        {
            System.out.println(e + "You fucked up mate");
        }
    }

    // Overlaid function that take user, showsSeen and favouriteShows as parameters
    // and writes them to the file userData.csv
    public void writeUserData(User user, ArrayList<String> showsSeen, ArrayList<String> favouriteShows)
    {
        try
        {
            FileWriter writer = new FileWriter("Data/userData.csv");

            // Write username and password
            writer.write(user.getUsername() + ", ");
            writer.write(user.getPassword() + ", ");

            // Write showsSeen to file
            for (String show : showsSeen)
            {
                writer.write(show + ", ");
            }

           // writer.write("; ");

            // Write favouriteShows to file
            for (String show : favouriteShows)
            {
                writer.write(show + ", ");
            }

            //writer.write("; ");

            writer.close();

        }
        catch (IOException e)
        {
            System.out.println(e + "An error has occurred");
        }
    }

    // the old overload writeUserData function

    /*public void writeUserData(User user, ArrayList<String> showsSeen, ArrayList<String> favouriteShows)
    {
        try
        {
            Scanner input = new Scanner(System.in);
            FileWriter writer = new FileWriter("Data/userData.csv", true);

            writer.write(user.getUsername() + ", ");
            writer.write(user.getPassword() + ", ");
            writer.write(showsSeen + ", ");
            writer.write(favouriteShows + ", \n");

            writer.close();

        }
        catch (IOException e)
        {
            System.out.println(e + "You fucked up mate");
        }
    }*/



}
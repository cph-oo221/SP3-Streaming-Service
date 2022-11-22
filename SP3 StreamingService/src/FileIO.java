import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileIO
{
    private File file = new File("Data/userData.csv");

    protected ArrayList<String> readMovieData()
    {
        File file = new File("Data/movieList.csv");
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);

            int i = 1;
            while (input.hasNextLine())
            {
                data.add(input.nextLine() + ";" + i + ";" + "\n");
                i++;
            }
        }
        catch (FileNotFoundException e)
        {
            data = null;
        }
        return data;
    }

    protected ArrayList<String> readSeriesData()
    {
        File file = new File("Data/seriesList.csv");
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);

            int i = 101;
            while (input.hasNextLine())
            {
                data.add(input.nextLine() + ";" + i + ";" + "\n");
                i++;
            }
        }
        catch (FileNotFoundException e)
        {
            data = null;
        }
        return data;
    }



    protected ArrayList<String> readUserData()
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



    protected void writeUserData(ArrayList<User> users)
    {
        try
        {
            FileWriter writer = new FileWriter("Data/userData.csv");

            for (User user: users)
            {
                writer.write(user.getUsername() + ",");
                writer.write(user.getPassword() + ";");

                if (user.getShowsSeen().size() > 0)
                {
                    for (String s : user.getShowsSeen())
                    {
                        writer.write(s + ",");
                    }
                    writer.write(";");
                } else writer.write("null;");

                if (user.getFavouriteShows().size() > 0)
                {

                    for (String s : user.getFavouriteShows())
                    {
                        writer.write(s + ",");
                    }
                    writer.write(";");
                } else writer.write("null;");

                writer.write('\n');
            }
                writer.close();


        }
        catch (IOException e)
        {
            System.out.println(e + "You fucked up mate");
        }
    }
    protected int userCounter()
    {
        try
        {
            int count = 0;
            File file = new File("Data/userData.csv");
            Scanner input = new Scanner(file);
            while (input.hasNextLine())
            {
                count++;
                input.nextLine();
            }
            return count;
        }
        catch (FileNotFoundException e)
        {
            return -1;
        }
    }

    protected boolean deleteFile() throws FileNotFoundException
    {
        return file.delete();
    }
}
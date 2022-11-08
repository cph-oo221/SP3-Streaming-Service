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
        File file = new File("SP03/Data/movieList.txt");
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
        File file = new File("SP03/Data/seriesList.txt");
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
        File file = new File("SP03/Data/userData.csv");
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

    public void writeUserData(User user)
    {
        try
        {
            Scanner input = new Scanner(System.in);
            FileWriter writer = new FileWriter("SP03/Data/userData.csv", true);

            writer.write(user.getUsername() + ", ");
            writer.write(user.getPassword() + ", \n");

            writer.close();

        }
        catch (IOException e)
        {
            System.out.println(e + "You fucked up mate");
        }
    }
}
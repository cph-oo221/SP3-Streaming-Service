import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO
{

    protected ArrayList<String> readMovieData()
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

    protected ArrayList<String> readSeriesData()
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

    //    >>>>>>> Muligvis unødvendig <<<<<<<
    protected ArrayList<String> readUserData0()
    {
        File file = new File("Data/userData.csv");
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                String s = input.nextLine();
                int i, j;
                i = 0;
                j = s.indexOf(";");
                if(i != -1 && j != -1)
                {
                    String value = s.substring(i, j);
                    data.add(value);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            data = null;
        }

        return data;

    }

    //    >>>>>>> Muligvis unødvendig <<<<<<<
    protected ArrayList<String> readUserData1()
    {
        File file = new File("Data/userData.csv");
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                String s = input.nextLine();
                int i, j;
                i = s.indexOf(";");
                j = s.indexOf("'");
                if(i != -1 && j != -1)
                {
                    String value = s.substring(i, j);
                    data.add(value);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            data = null;
        }

        return data;
    }

    //    >>>>>>> Muligvis unødvendig <<<<<<<
    protected ArrayList<String> readUserData2()
    {
        File file = new File("Data/userData.csv");
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                String s = input.nextLine();
                int i, j;
                i = s.indexOf("*");
                j = s.indexOf("!");
                if(i != -1 && j != -1)
                {
                    String value = s.substring(i, j);
                    data.add(value);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            data = null;
        }

        return data;

    }


    protected void writeUserData(User user)
    {
        try
        {
            FileWriter writer = new FileWriter("Data/userData.csv", true);

            writer.write(user.getUsername() + ", ");
            writer.write(user.getPassword() + "; \n");

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
}
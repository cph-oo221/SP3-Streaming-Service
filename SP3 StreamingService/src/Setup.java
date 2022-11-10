import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Setup
{
    TextUI textUI = new TextUI();
    FileIO fileIO = new FileIO();

    protected User runSetUp()
    {
        textUI.displayMessage("Welcome to fedFlix! Press 1 to register new user, or press 2 for login");
        textUI.displayMessage(fileIO.readUserData().toString());
        String choice = textUI.getUserInput();

        if(choice.equals("1"))
        {
            return register();
        }
        else
        {
            return login();
        }
    }

    protected User login()
    {
        boolean running;
        running = true;
        ArrayList<User> users = createUsers();
        while(running)
        {
        String name = textUI.getUserInput("Please enter your Username");
        String pass = textUI.getUserInput("Please enter your Password");
            for (User i : users)
            {
                textUI.displayMessage("User :"+i);
                if (name.equalsIgnoreCase(i.getUsername()) && pass.equals(i.getPassword())) {
                    textUI.displayMessage("Login successful");
                    textUI.displayMessage("****************");
                    textUI.displayMessage("****************");
                    return i;
                }
            }
                textUI.displayMessage("Username or Password does not exist. Please try again.");
                break;
        }
        return null;

    }

    private ArrayList<User> createUsers()
    {
        textUI.displayMessage("readuserdata ingen fejl");

        ArrayList<User> users = new ArrayList<>();

        ArrayList<String> userData = fileIO.readUserData();

        textUI.displayMessage("readShowsSeen ingen fejl");
        ArrayList<String> showsSeenRaw = fileIO.readUserData1();

        textUI.displayMessage("readFavouriteShowsRaw  ingen fejl");
        ArrayList<String> favouriteShowsRaw = fileIO.readUserData2();
        ArrayList<String> favouriteShows = new ArrayList<>();
        ArrayList<String> showsSeen = new ArrayList<>();

        textUI.displayMessage("readuserdata ingen fejl");
        int entries = fileIO.userCounter();
        String[][] IDs = new String[entries][];
        textUI.displayMessage("entries talt uden fejl");



        for (int i = 0; i < entries ; i++ )
        {
<<<<<<< Updated upstream
            String trimmed = s.replaceAll("[\\\\{}]","").replaceAll(";","").replaceAll(" ","");
            System.out.println(trimmed);
            String[] arr = trimmed.split(",");
            User user = new User (arr[0], arr[1]);
=======
            for (int j = 0; j < userData.size(); j++)
            {
                String trimmed = userData.get(j).replaceAll("[\\\\{}]", "").replaceAll(" ", "").replaceAll(";","");
                String[] arr = trimmed.split(",");
                textUI.displayMessage(arr+"Tilføjet");
                IDs[j] = arr;
            }
            textUI.displayMessage("ID [] genereret uden fejl");
            textUI.displayMessage("ID"+ IDs.toString());
            for (int j = 0; j < showsSeenRaw.size(); j++)
            {
                String trimmed = showsSeenRaw.get(j).replaceAll("[\\\\{}]", "").replaceAll(" ", "");
                String[] arr = trimmed.split(",");
                for (int k = 0; k < arr.length; k++)
                {
                    showsSeen.add(arr[i]);
                }
            }
            textUI.displayMessage("showsSeen genereret uden fejl");
            textUI.displayMessage("showsSeen :" + showsSeen.toString());
            for (int j = 0; j < favouriteShowsRaw.size(); j++)
            {
                String trimmed = favouriteShowsRaw.get(j).replaceAll("[\\\\{}]", "").replaceAll(" ", "");
                String[] arr = trimmed.split(",");
                for (int k = 0; k < arr.length; k++)
                {
                    favouriteShows.add(arr[j]);
                }
            }
            textUI.displayMessage("favouriteShows genereret uden fejl");
            textUI.displayMessage("favouriteShows :" + favouriteShows.toString());


            User user = new User(IDs[i][0], IDs[i][1], showsSeen, favouriteShows);
>>>>>>> Stashed changes
            users.add(user);
            textUI.displayMessage("Bruger genereret og tilføjet uden fejl");
            textUI.displayMessage(i+":"+ user.toString());
        }
        textUI.displayMessage("Alt klaret, brugere returneret");
        return users;
    }

    private User register()
    {
        textUI.displayMessage("Register new user:");
        String name = textUI.getUserInput("Username: ");
        // userNames.add(name);
        String pass = textUI.getUserInput("Password: ");
        if(!name.contains(",") && !pass.contains(","))
        {
            User user = new User(name, pass);
            fileIO.writeUserData(user);
            return user;

        }
        else
        {
            textUI.displayMessage("Please only use letters and numbers");
            register();
        }
        return null;
    }
}
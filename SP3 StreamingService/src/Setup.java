import java.util.ArrayList;
import java.util.Arrays;

public class Setup
{
    private TextUI textUI = new TextUI();
    private IO io = new IO();
    private ArrayList<User> users = new ArrayList<>();

    protected User runSetUp()
    {
        textUI.displayMessage("""
                    Welcome to fedFlix!
                    
                    ********************
                    Press 1 to register
                    Press 2 to login
                    Press 3 to exit
                    ********************""");

        String choice = textUI.getUserInput();
        switch (choice)
        {
            case "1":
                return register();
            case "2":
                return login();
            case "3":
                return exit();
            default:
                textUI.displayMessage("Invalid input, please try again");
                return runSetUp();
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
                if (name.equalsIgnoreCase(i.getUsername()) && pass.equals(i.getPassword())) {
                    textUI.displayMessage("****************");
                    textUI.displayMessage("Login successful");
                    textUI.displayMessage("****************");
                    return i;
                }
            }
                textUI.displayMessage("Username or Password does not exist. Please try again.");
        }
        return null;
    }

    private ArrayList<User> createUsers() {
        ArrayList<String> userData = io.readUserData();

       // int entries = fileIO.userCounter();

        for (String userDatum : userData)
        {

            String[] allTheData = userDatum.split(";");


            ArrayList<String> showsSeen = new ArrayList<>();
            ArrayList<String> watchList = new ArrayList<>();


            String[] ID = allTheData[0].split(",");

            User user = new User(ID[0], ID[1]);

            if (!allTheData[1].equals("null"))
            {
                String[] showsSeenArr = allTheData[1].split(",");
                showsSeen.addAll(Arrays.asList(showsSeenArr));
                user.setShowsSeen(showsSeen);
            }


            if (!allTheData[2].equals("null"))
            {
                String[] watchListArr = allTheData[2].split(",");
                watchList.addAll(Arrays.asList(watchListArr));
                user.setFavouriteShows(watchList);
            }

            users.add(user);


            if (allTheData.length != 3)
            {
                textUI.displayMessage("User data error!");
            }

        }
        return users;
    }

    private User register()
    {
        users = createUsers();

        while (true)
        {
            boolean exists = false;
            textUI.displayMessage("Register new user:");
            String name = textUI.getUserInput("Username: ");
            // userNames.add(name);
            String pass = textUI.getUserInput("Password: ");

            for (User u : users)
            {
                if (u.getUsername().equalsIgnoreCase(name))
                {
                    exists = true;
                }

            }



            if (!name.contains(",") && !pass.contains(","))
            {
                if (!exists)
                {
                    User user = new User(name, pass);
                    users.add(user);
                    return user;
                }
                else textUI.displayMessage("User already exists");
            }
            else
            {
                textUI.displayMessage("Please only use letters and numbers");
            }
        }
    }

    private User exit()
    {
        // Display a goodbye message to the user
        textUI.displayMessage("Goodbye! have a nice day, and hope to see you again soon!");
        // Make the program exit (aka just terminates the program because it's a console app)
        System.exit(0);
        // Return null because the method is supposed to return a User object
        return null;
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }
}
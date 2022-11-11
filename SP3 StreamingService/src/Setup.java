import java.util.ArrayList;
import java.util.Arrays;

public class Setup
{
    TextUI textUI = new TextUI();
    FileIO fileIO = new FileIO();

    protected User runSetUp()
    {
        textUI.displayMessage("Welcome to fedFlix! Press 1 to register new user, or press 2 for login");
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

    private ArrayList<User> createUsers() {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String> userData = fileIO.readUserData();

        int entries = fileIO.userCounter();
        textUI.displayMessage("entries talt uden fejl :" +entries);

        for (int i = 0; i < entries; i++) {

            String[] allTheData = userData.get(i).split(";");
            ArrayList<String> showsSeen = new ArrayList<>();
            ArrayList<String> watchList = new ArrayList<>();


            String[] ID = allTheData[0].replaceAll(" ","").split(",");

            if (allTheData.length >= 2)
            {
                String[] showsSeenArr = allTheData[1].split(",");

                showsSeen.addAll(Arrays.asList(showsSeenArr));

            }


            if (allTheData.length >= 3)
            {
                textUI.displayMessage(allTheData[2]);
                String[] watchListArr = allTheData[2].split(",");
                watchList.addAll(Arrays.asList(watchListArr));
            }


            if(allTheData.length == 1)
            {
                User user = new User(ID[0],ID[1],showsSeen);
                users.add(user);
            }
            if(allTheData.length == 2 )
            {
                User user = new User(ID[0], ID[1], showsSeen, watchList);
                users.add(user);
            }
            else
            {
                User user = new User(ID[0], ID[1]);
                users.add(user);
            }
        }
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
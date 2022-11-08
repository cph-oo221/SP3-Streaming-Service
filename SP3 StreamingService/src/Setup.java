import java.util.ArrayList;

public class Setup
{
    static ArrayList<String> userNames;
    static ArrayList<String> passWords;

    TextUI textUI = new TextUI();
    FileIO fileIO = new FileIO();

    protected User runSetUp()
    {
        textUI.displayMessage("Welcome to fedFlix! Press 1 to register new user, or press 2 for login");
        String choice = textUI.getUserInput();

        if(choice.equals("1"))
        {
            register();
        }
        else
        {
            login();
        }

        User user = new User("temp","temp");
        return user;
    }

    protected User login() {
        String name;
        String pass;
        name = textUI.getUserInput("Please enter your Username");
        pass = textUI.getUserInput("Please enter your Password");
        ArrayList<User> users = createUsers();
        for (User i : users)
        {
            if(name == i.getUsername() && pass == i.getPassword())
            {
                textUI.displayMessage("Login successful");
                return i;
            }
            else
            {
                textUI.displayMessage("Username or Password does not exist. Please try again.");
                login();
            }

        }
        return null;
    }

    private ArrayList<User> createUsers()
    {
        ArrayList<String> userData = fileIO.readUserData();
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < userData.size(); i++) {
            String[] s = userData.get(i).split(",");
            User user = new User(s[0], s[1]);
            users.add(user);
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
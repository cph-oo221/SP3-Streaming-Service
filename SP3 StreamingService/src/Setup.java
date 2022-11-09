import java.util.ArrayList;

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

    protected User login() {
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
                } else
                {
                    textUI.displayMessage("Username or Password does not exist. Please try again.");
                    break;
                }
            }
        }
        return null;
    }

    private ArrayList<User> createUsers()
    {
        ArrayList<String> userData = fileIO.readUserData();
        ArrayList<User> users = new ArrayList<>();

        for (String s : userData)
        {
            String trimmed = s.replaceAll("[\\\\{}]","").replaceAll(";","").replaceAll(" ","");
            String[] arr = trimmed.split(",");
            User user = new User (arr[0], arr[1]);
            users.add(user);
        }
        return users;
        //return null;
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
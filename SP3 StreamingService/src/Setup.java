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

        User user = new User("temp", "temp");
        return user;
    }

    protected User login() {
        String name;
        String pass;
        name = textUI.getUserInput("Please enter your Username");
        pass = textUI.getUserInput("Please enter your Password");
        ArrayList<User> users = new ArrayList<>();
        users = createUsers();
        for (User i : users)
        {

            //SLET DETTE - PRINT TEST
            System.out.println("******");
            System.out.println(name);
            System.out.println(pass);
            System.out.println("*****");
            System.out.println(i.getUsername());
            System.out.println(i.getPassword());
            //SLET OVENSTÅENDE - PRINT TEST


            if(name.equals(i.getUsername()) && pass.equals(i.getPassword()))
            {
                textUI.displayMessage("Login successful");
                return i;
            }
            else
            {
                textUI.displayMessage("Username or Password does not exist. Please try again.");
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
            String trimmed = s.replaceAll("[\\\\p{P}]","").replaceAll(";","").replaceAll(" ","");
            System.out.println(trimmed);
            String[] arr = trimmed.split(",");
            User user = new User (arr[0], arr[1]);
            users.add(user);
            System.out.println(user);
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
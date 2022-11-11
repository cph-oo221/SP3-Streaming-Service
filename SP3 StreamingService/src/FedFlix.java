import java.util.ArrayList;

public class FedFlix
{
    ArrayList<User> users;
    public void runFedFlix()
    {
        User currentUser = setUp();
        mainMenu(currentUser);
    }

    private User setUp()
    {
        Setup setUp = new Setup();
        return setUp.runSetUp();
       // users = setUp.getUsers();

    }

    private void mainMenu(User currentUser)
    {
        MainMenu mainMenu = new MainMenu(currentUser, users);
        mainMenu.runMainMenu();
    }
}
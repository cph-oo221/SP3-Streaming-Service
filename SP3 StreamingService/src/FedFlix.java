import java.util.ArrayList;

public class FedFlix
{
    Setup setUp = new Setup();
    public void runFedFlix()
    {
        User currentUser = setUp();
        mainMenu(currentUser);
    }

    private User setUp()
    {
        return setUp.runSetUp();
    }

    private void mainMenu(User currentUser)
    {
        ArrayList<User> users = setUp.getUsers();
        MainMenu mainMenu = new MainMenu(currentUser, users);
        mainMenu.runMainMenu();
    }
}
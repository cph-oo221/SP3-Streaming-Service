public class FedFlix
{
    public void runFedFlix()
    {
        User currentUser = setUp();

        mainMenu(currentUser);
    }

    private User setUp()
    {
        Setup setUp = new Setup();
        User user = new User("temp", "temp");
        user=setUp.runSetUp();
        return user;
    }

    private void mainMenu(User currentUser)
    {
        MainMenu mainMenu = new MainMenu(currentUser);
        mainMenu.runMainMenu();
    }
}

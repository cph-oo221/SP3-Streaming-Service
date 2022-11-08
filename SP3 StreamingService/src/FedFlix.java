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
        setUp.runSetUp();
        User user = new User("temp", "temp");
        return user;
    }

    private void mainMenu(User currentUser)
    {
        MainMenu mainMenu = new MainMenu(currentUser);
        mainMenu.runMainMenu();
    }
}

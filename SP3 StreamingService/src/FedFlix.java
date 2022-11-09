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
        return setUp.runSetUp();
    }

    private void mainMenu(User currentUser)
    {
        MainMenu mainMenu = new MainMenu(currentUser);
        mainMenu.runMainMenu();
    }
}

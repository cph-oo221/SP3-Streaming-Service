public class FedFlix
{
    Setup setUp = new Setup();
    public void runFedFlix()
    {
        setUp();
        //mainMenu(setUp.login());

    }

    private User setUp()
    {
        Setup setUp = new Setup();
        setUp.runSetUp();
        User user = new User("temp", "temp");
        return user;
    }
}

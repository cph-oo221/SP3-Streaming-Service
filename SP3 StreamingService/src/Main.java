import java.util.ArrayList;

public class Main
{
    static FedFlix fedFlix = new FedFlix();
    public static void main(String[] args)
    {
        fedFlix.runFedFlix();


        // test code
        Setup setup = new Setup();
        // User user = setup.runSetUp();
        User user = new User("Oskar", "123");
        user.addShowsSeen("The Office");
        user.addShowsSeen("Titanic");
        user.addShowsSeen("the flash");
        user.addShowsSeen("The Thick of It");
        user.addFavouriteShows("The Office");
        user.saveUserData(user);

    }
}
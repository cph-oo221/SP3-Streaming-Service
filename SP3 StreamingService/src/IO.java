public class IO
{
    private DatabaseIO databaseIO = new DatabaseIO();
    private FileIO fileIO = new FileIO();

    public void run()
    {
        databaseIO.establishConnection();
    }

}



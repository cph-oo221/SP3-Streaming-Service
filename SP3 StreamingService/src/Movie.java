public class Movie extends Media
{
    private final String type = "Movie";

    public Movie(String name, String year, String[] genre, String rating, int id)
    {
        super(name, year, genre, rating, id);
    }

    @Override
    public String toString()
    {
        return super.toString() + ", " + "Type: " + type + '\n';
    }

}

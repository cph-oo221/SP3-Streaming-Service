public class Movie extends Media
{
    final String type = "Movie";

    public Movie(String name, String year, String[] genre, String rating)
    {
        super(name, year, genre, rating);
    }

    //TODO: Fix weird comma on newline
    @Override
    public String toString()
    {
        return super.toString() + ", " + "Type: " + type + '\n';
    }

}

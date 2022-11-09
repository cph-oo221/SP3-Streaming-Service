import java.io.IOException;
import java.net.URISyntaxException;

public interface IMedia
{
    public void play() throws URISyntaxException, IOException;

    public String toString();

    public String getName();

    public String getYear();

    public String[] getGenre();

    public String getRating();

}

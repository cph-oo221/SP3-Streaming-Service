import java.io.IOException;
import java.net.URISyntaxException;

public interface IMedia
{
    void play() throws URISyntaxException, IOException;

    String toString();

    String getName();

    String getYear();

    String[] getGenre();

    String getRating();

    int getId();

}

import java.util.ArrayList;

public class SearchFunction
{
   User currentUser;
   ArrayList<IMedia> media;
    public SearchFunction(User currentUser, ArrayList<IMedia> media)
    {
        this.currentUser = currentUser;
        this.media = media;
    }

    //TODO Test searchfunction
    public ArrayList<IMedia> searchMedia(String input)
    {
        ArrayList<IMedia> output = new ArrayList<>();

        for (IMedia m: media)
        {
            if (m.getName().toLowerCase().contains(input.toLowerCase()))
            {
                output.add(m);
            }
        }
        return output;
    }
}

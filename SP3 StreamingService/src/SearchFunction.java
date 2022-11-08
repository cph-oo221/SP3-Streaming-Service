import java.util.ArrayList;

public class SearchFunction
{
    TextUI textUI = new TextUI();
   User currentUser;
   ArrayList<IMedia> media;
    public SearchFunction(User currentUser, ArrayList<IMedia> media)
    {
        this.currentUser = currentUser;
        this.media = media;
    }


    public ArrayList<IMedia> searchMedia(String input)
    {
        ArrayList<IMedia> output = new ArrayList<>();

        for (IMedia m: media)
        {
            if (m.getName().contains(input))
            {
                output.add(m);
            }
        }
        return output;
    }
}

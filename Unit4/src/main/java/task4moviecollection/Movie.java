package task4moviecollection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable{
    public final String movieName;
    public final List<String> mainActorsList = new ArrayList<>();

    Movie(String movieName, String... actorName){
        if (movieName==null||actorName==null)
            throw new IllegalArgumentException("Movie's name or actor's name is null");
        this.movieName = movieName;
        for (String s : actorName)
            mainActorsList.add(s);
    }

}

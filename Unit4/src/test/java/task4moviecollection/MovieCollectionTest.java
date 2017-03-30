package task4moviecollection;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieCollectionTest {
    MovieCollection collection = new MovieCollection(new Movie("Indian coder", "Sergey Bezrukov", "Dmitry Haratian"));
    private static final String FILETOWRITE =  "src\\main\\resources\\CrazyMovie.txt";
    @Test
    public void saveCollection() throws Exception {
        collection.addMovie(new Movie("Very smelling code", "Sergey Bezrukov", "Dmitry Bilan"));
        collection.saveCollection(FILETOWRITE);

    }
    @Test
    public void loadCollection() throws Exception {
        collection.addMovie(new Movie("Very smelling code", "Sergey Bezrukov", "Dmitry Bilan"));
        collection.saveCollection(FILETOWRITE);

        MovieCollection risenColl =  collection.loadCollection(FILETOWRITE);
        assertEquals("Sergey Bezrukov", risenColl.getMovie("Indian coder").mainActorsList.get(0));
        assertEquals("Dmitry Bilan", risenColl.getMovie("Very smelling code").mainActorsList.get(1));


    }



}
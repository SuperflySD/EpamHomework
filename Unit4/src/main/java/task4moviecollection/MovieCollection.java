package task4moviecollection;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieCollection implements Serializable {
    private List<Movie> moviesList = new ArrayList<>();

    public MovieCollection(Movie movie) {
        if (movie == null)
            throw new IllegalArgumentException("Movie can't be null");
        moviesList.add(movie);
    }

    public void saveCollection(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public MovieCollection loadCollection(String fileName) throws IOException, ClassNotFoundException {
        MovieCollection collection = null;
        String str = null;
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        collection = (MovieCollection) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return collection;
    }


    public MovieCollection addMovie(Movie movie) {
        if (movie == null)
            throw new IllegalArgumentException("Movie can't be null");
        moviesList.add(movie);
        return this;
    }

    public Movie getMovie(String movieName) {
        if (movieName == null)
            throw new IllegalArgumentException("Movie's name can't be null");
        for (Movie movie : moviesList)
            if (movie.movieName.equals(movieName))
                return movie;

        return null;
    }


}

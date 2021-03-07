package com.example.REST.repositories;

import com.example.REST.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    static Long currentId = 4L;

    static final List<Movie> movies = new LinkedList<>(Arrays.asList(
            new Movie(1L, "Pulp Fiction", "Quentin Tarantino", "1994"),
            new Movie(2L, "Proces Siódemki z Chicago", "Aaron Sorkin", "2020"),
            new Movie(3L, "Fight Club", "David Fincher", "1999")
    ));

    public List<Movie> find() {
        return movies;
    }

    public Movie find(Long id) {
        return movies.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public Movie save(Movie movie) {
        movie.setId(currentId++);
        movies.add(movie);
        return movie;
    }

    public Movie update(Movie movie, Long id) {
        Movie movieToUpdate = find(id);

        if (movieToUpdate == null) {
            return null;
        }

        movieToUpdate.setTitle(movie.getTitle());
        movieToUpdate.setDirector(movie.getDirector());
        movieToUpdate.setYear(movie.getYear());

        return movieToUpdate;
    }

    public void delete(Long id) {
        Movie movie = find(id);
        movies.remove(movie);
    }


}


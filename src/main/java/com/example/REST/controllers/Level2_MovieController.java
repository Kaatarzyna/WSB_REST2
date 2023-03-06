package com.example.REST.controllers;

import com.example.REST.models.Movie;
import com.example.REST.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("level2")
public class Level2_MovieController {

    final MovieRepository movieRepository;

    public Level2_MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("movies")
    ResponseEntity<List<Movie>> index() {
        return ResponseEntity.ok(movieRepository.find());
    }

    @PostMapping("movies")
    ResponseEntity<Movie> save(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        if (savedMovie != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @GetMapping("movies/{id}")
    ResponseEntity<Movie> show(@PathVariable Long id) {
        Movie movie = movieRepository.find(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("movies/{id}")
    ResponseEntity<Movie> update(@RequestBody Movie movie, @PathVariable Long id) {
        Movie updatedMovie = movieRepository.update(movie, id);

        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("movies/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        movieRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}

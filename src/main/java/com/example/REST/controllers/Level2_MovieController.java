package com.example.REST.controllers;

import com.example.REST.models.Movie;
import com.example.REST.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("level2")
public class Level2_MovieController {

    final MovieRepository movieRepository;

    public Level2_MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    List<Movie> index() {
        return movieRepository.find();
    }

    @GetMapping("{id}")
    Movie show(@PathVariable Long id, HttpServletResponse response) {
        Movie movie = movieRepository.find(id);
        if (movie != null) {
            return movie;
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return movieRepository.find(id);
        }
    }

    @PutMapping("{id}")
    Movie update(@RequestBody Movie movie, @PathVariable Long id, HttpServletResponse response) {
        Movie updatedMovie = movieRepository.update(movie, id);

        if (updatedMovie != null) {
            return updatedMovie;
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    @PostMapping
    Movie save(@RequestBody Movie movie, HttpServletResponse response) {
        Movie savedMovie = movieRepository.save(movie);
        if (savedMovie != null) {
            response.setStatus(HttpStatus.CREATED.value());
            return savedMovie;
        } else {
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            return null;
        }
    }

    @DeleteMapping("{id}")
    String delete(@PathVariable Long id, HttpServletResponse response) {
        movieRepository.delete(id);
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return "OK";
    }
}

package com.example.REST.controllers;

import com.example.REST.models.Movie;
import com.example.REST.repositories.MovieRepository;
import org.springframework.web.bind.annotation.*;

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
    Movie show(@PathVariable Long id) {
        return movieRepository.find(id);
    }

    @PutMapping("{id}")
    Movie update(@RequestBody Movie movie, @PathVariable Long id) {
        return movieRepository.update(movie, id);
    }

    @PostMapping
    Movie save(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @DeleteMapping("{id}")
    String delete(@PathVariable Long id) {
        movieRepository.delete(id);
        return "OK";
    }
}

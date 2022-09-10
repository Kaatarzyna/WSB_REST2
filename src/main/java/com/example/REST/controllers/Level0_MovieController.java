package com.example.REST.controllers;

import com.example.REST.models.Movie;
import com.example.REST.repositories.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("level0")
public class Level0_MovieController {

    final MovieRepository movieRepository;

    public Level0_MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping("movieService")
    String movieService(@RequestBody JsonNode requestJson) {

        String method = requestJson.get("method").asText();
        Movie movie = null;
        Long id = null;

        if (requestJson.has("movie")) {
            JsonNode movieJson = requestJson.get("movie");
            movie = new Movie(movieJson.get("title").asText(),
                    movieJson.get("director").asText(),
                    movieJson.get("year").asText());

            id = movieJson.has("id") ? movieJson.get("id").asLong() : null;
        }

        switch (method) {
            case "save":
                return movieRepository.save(movie).toString();
            case "update":
                return movieRepository.update(movie, id).toString();
            case "delete":
                movieRepository.delete(id);
                return "OK";
            case "get":
                return movieRepository.find().stream().map(Movie::toString).collect(Collectors.joining(" | "));
            case "findDirectors":
                return movieRepository.find().stream().map(Movie::getDirector).collect(Collectors.joining(" | "));
            default:
                return "OK";
        }
    }
}


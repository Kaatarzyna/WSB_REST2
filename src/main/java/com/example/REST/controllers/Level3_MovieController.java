package com.example.REST.controllers;

import com.example.REST.models.Movie;
import com.example.REST.repositories.MovieRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("level3")
public class Level3_MovieController {

    final MovieRepository movieRepository;

    public Level3_MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    CollectionModel<EntityModel<Movie>> index() {

        List<EntityModel<Movie>> movies = movieRepository.find()
                .stream()
                .map(m -> EntityModel.of(m,
                        linkTo(methodOn(Level3_MovieController.class).show(m.getId())).withSelfRel()))
                .collect(Collectors.toList());

        return CollectionModel.of(movies,
                linkTo(methodOn(Level3_MovieController.class).index()).withSelfRel());
    }

    @GetMapping("{id}")
    ResponseEntity<?> show(@PathVariable Long id) {

        Movie movie = movieRepository.find(id);

        if (movie != null) {
            EntityModel<Movie> movieModel = EntityModel.of(movie,
                    linkTo(methodOn(Level3_MovieController.class).show(id)).withSelfRel(),
                    linkTo(methodOn(Level3_MovieController.class).index()).withRel("movies"));

            return ResponseEntity.ok().body(movieModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    ResponseEntity<?> update(@RequestBody Movie movie, @PathVariable Long id) {
        Movie updatedMovie = movieRepository.update(movie, id);

        if (updatedMovie != null) {
            EntityModel<Movie> movieModel = EntityModel.of(updatedMovie,
                    linkTo(methodOn(Level3_MovieController.class).show(id)).withSelfRel());

            return ResponseEntity.ok().body(movieModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<?> save(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        if (savedMovie != null) {
            EntityModel<Movie> movieModel = EntityModel.of(savedMovie,
                    linkTo(methodOn(Level3_MovieController.class).show(savedMovie.getId())).withSelfRel());

            return ResponseEntity.created(movieModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(movieModel);
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        movieRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}

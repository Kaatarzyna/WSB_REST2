package com.example.REST.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
@NoArgsConstructor
public class Movie {

    Long id;
    String title;
    String director;
    String year;

    public Movie(Long id, String title, String director, String year) {
        this(title, director, year);
        this.id = id;
    }

    public Movie(String title, String director, String year) {
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public String toString() {
        return MessageFormat.format("{0} - {1}, {2}", title, director, year);
    }
}
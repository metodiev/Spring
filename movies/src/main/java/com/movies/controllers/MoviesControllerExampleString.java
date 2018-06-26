package com.movies.controllers;


import com.movies.models.Movie;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apiexample")
public class MoviesControllerExampleString {



    List<String> movies;

    public MoviesControllerExampleString() {
        this.movies = new ArrayList<String>();

        int count = 10;
        for (int i = 0; i < count; i++) {
            movies.add("Movie #" + (i + 1));
        }
    }
@RequestMapping(value = "/getmovies", method = RequestMethod.GET)
    public List<String> getAll() {
        return this.movies;
    }
//if you want only json for clint
//@RequestMapping(value = "addmovie", method = RequestMethod.POST, produces = "application/json")
@RequestMapping(value = "addmovie", method = RequestMethod.POST)
    public String  addMovie(@RequestBody String newMovie){
        this.movies.add(newMovie);

        return newMovie;
    }
}

package com.movies.controllers;

import com.movies.models.Movie;
import com.movies.utils.IdGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MoviesController {

    List<Movie> movies = new ArrayList<>();
    IdGenerator idGenerator;

    public MoviesController() {
        this.movies = new ArrayList<Movie>();
        this.idGenerator = new IdGenerator();
        int count = 10;

        for (int i = 0; i < count; i++) {
            Movie movie = new Movie();

            movie.setId(this.idGenerator.getNextId());
            movie.setTitle("Movie #" + (i + 1));
            movie.setDescription("Description for movie# " + (i + 1));
         //   movie.setRating(1 + (i % 5));
       //     movie.setVotesCOunt(5);
            movies.add(movie);
        }
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<Movie> getAll() {
        return this.movies;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public Movie addMovie(@RequestBody Movie newMovie) {
        newMovie.setId(this.idGenerator.getNextId());
        this.movies.add(newMovie);
        return newMovie;
    }

    //findmovie by id
    @RequestMapping(value = "/movies/{movieid}", method = RequestMethod.GET)
    public Movie getById(@PathVariable(value = "movieid") int id) {
        Movie movie = null;
        boolean isMovieFOund = false;

        for (Movie currenMovie : this.movies) {
            if (currenMovie.getId() == id) {
                return currenMovie;
                //movie = currenMovie;
                //isMovieFOund = true;
                //break;
            }
        }

        if (!isMovieFOund) {
            //return null;
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
//check stackoverflow
        //https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
        return null;
        // return movie;
    }

    @RequestMapping(value = "/movies/{movieId}", method = RequestMethod.PUT)
    public Movie voteForMovie(@PathVariable(value = "movieid") int id, @RequestBody int rating) {
        Movie movie = this.getById(id);
        double oldRating = movie.getRating();
        int newVotesCount = movie.getVotesCOunt() + 1;

        double newRating = (oldRating + rating) / newVotesCount;
        //double newRating = (oldRating * movie.getVotesCOunt() + rating) / (movie.getVotesCOunt() + 1);

        movie.setRating(newRating);
        movie.setVotesCOunt(newVotesCount);

        return movie;
    }
}

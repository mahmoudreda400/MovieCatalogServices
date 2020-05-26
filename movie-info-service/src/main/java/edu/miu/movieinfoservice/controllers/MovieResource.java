package edu.miu.movieinfoservice.controllers;

import edu.miu.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){

        //here should call external api to get the movie info like movieDBk
        return new Movie(11,movieId,"avangers");

    }
}

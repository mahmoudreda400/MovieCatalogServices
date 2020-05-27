package edu.miu.moviecatalogservice.controllers;

import edu.miu.moviecatalogservice.fegienProxy.RatingProxy;
import edu.miu.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFeign {

    @Autowired
    RatingProxy ratingProxy;

    @GetMapping(value = "/testFeign/movie/{movieId}")
    public Rating getMovieRating(@PathVariable String movieId){
        System.out.println(">>> calling getMovieRatign -> feign");
        return ratingProxy.getMovieRating(movieId);
    }

    @GetMapping(value = "/testFeign/saveRating")
    public Rating getMovieRating(){
        System.out.println(">>> calling saveRating -> feign");
        Rating rating = new Rating();
        rating.setMovieId("1009");
        rating.setRating(8);
        return ratingProxy.saveRating(rating);
    }
}

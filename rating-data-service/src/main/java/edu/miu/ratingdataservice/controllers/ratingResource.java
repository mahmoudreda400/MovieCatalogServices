package edu.miu.ratingdataservice.controllers;

import edu.miu.ratingdataservice.dao.UserRating;
import edu.miu.ratingdataservice.models.Rating;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingData")
public class ratingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("555", 3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRatings(ratings);
        return userRating;
    }

    @RequestMapping(value = "/saveRating", method = RequestMethod.POST)
    public Rating saveRating(@RequestBody Rating rating){
        System.out.println(">>> rating saved ssuccessfully");
        return rating;
    }

}

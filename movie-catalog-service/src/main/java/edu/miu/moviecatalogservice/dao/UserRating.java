package edu.miu.moviecatalogservice.dao;

import edu.miu.moviecatalogservice.models.Rating;

import java.util.List;

public class UserRating {

    private List<Rating> userRatings;

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }
}

package edu.miu.moviecatalogservice.fegienProxy;

import edu.miu.moviecatalogservice.models.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "rating-data-service", url = "http://localhost:8083")
public interface RatingProxy {

    @GetMapping("/ratingData/movie/{movieId}")
    public Rating getMovieRating(@PathVariable String movieId);

    @PostMapping("/ratingData/saveRating")
    public Rating saveRating(Rating rating);
}

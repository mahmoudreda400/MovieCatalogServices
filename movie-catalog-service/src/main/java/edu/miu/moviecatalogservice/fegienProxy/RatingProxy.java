package edu.miu.moviecatalogservice.fegienProxy;

import edu.miu.moviecatalogservice.models.Rating;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "rating-data-service")
@FeignClient(name = "zuul-api-getway")
@RibbonClient(name = "rating-data-service")

public interface RatingProxy {

    //    @GetMapping("/ratingData/movie/{movieId}")
    @GetMapping("/rating-data-service/ratingData/{movieId}")
    public Rating getMovieRating(@PathVariable String movieId);

    //    @PostMapping("/ratingData/saveRating")
    @PostMapping("/rating-data-service/ratingData/saveRating")
    public Rating saveRating(Rating rating);
}

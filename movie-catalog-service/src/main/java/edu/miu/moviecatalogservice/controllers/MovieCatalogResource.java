package edu.miu.moviecatalogservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import edu.miu.moviecatalogservice.dao.UserRating;
import edu.miu.moviecatalogservice.models.CatalogItem;
import edu.miu.moviecatalogservice.models.Movie;
import edu.miu.moviecatalogservice.models.Rating;
import edu.miu.moviecatalogservice.services.CatalogInfoService;
import edu.miu.moviecatalogservice.services.RatingInfoServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private CatalogInfoService catalogInfoService;

    @Autowired
    private RatingInfoServie ratingInfoServie;

    /////////////////////////////////////////////////////////////////////
    @RequestMapping("/rest/{userId}")
    //if circut breaker happen it will call hard coded method ("getFallbackCatalog") to return by a response instead of throw an error
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //resttemplate should annotated by @LoadBalanced
        //we use the eurka client name insteade of use the url
        // (may be there are more than jar running on diffrent port so we don't care about which machine)
        UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingData/users/" + userId, UserRating.class);

        return userRating.getUserRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(1, movie.getMovieName(), "desc", rating.getRating());
        }).collect(Collectors.toList());

    }

    //this method will be called if circut breaker happen at getCatalog method
    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        return Arrays.asList(new CatalogItem(0, "no movies", "", 0));
    }

///////////////////////////////////////////////////////////////////////////////
    // replace above block with this block
//    here we will seperate the above method to three method evey one to call a web services
//    because if one webservices is slow or fail the other one will excuted and return data

    //getUserRating method and getCatalogItem method must be in seperated class
    // because of the proxy class be enabled to handle the process of hystrix and fallback

    @RequestMapping("/rest2/{userId}")
    public List<CatalogItem> getCatalog2(@PathVariable("userId") String userId) {

        UserRating userRating = ratingInfoServie.getUserRating(userId);

        return userRating.getUserRatings().stream().map(rating -> catalogInfoService.getCatalogItem(rating))
                .collect(Collectors.toList());

    }

    ///////////////////////////////////////////////////////////////

    @RequestMapping("/reactive/{userId}")
    public List<CatalogItem> getCatalogReactive(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsData/users/" + userId, UserRating.class);

        return userRating.getUserRatings().stream().map(rating -> {
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movie/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new CatalogItem(1, movie.getMovieName(), "desc", rating.getRating());
        }).collect(Collectors.toList());

    }
}

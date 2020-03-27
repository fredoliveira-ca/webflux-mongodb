package com.fredoliveira.webfluxmongodb.web.controller;

import com.fredoliveira.webfluxmongodb.domain.Movie;
import com.fredoliveira.webfluxmongodb.domain.MovieEvent;
import com.fredoliveira.webfluxmongodb.domain.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/movies")
public class RestController {

    private final MovieService movieService;

    public RestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}/events", produces = TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id) {
        return movieService.events(id);
    }

    @GetMapping(value = "/{id}")
    Mono<Movie> findById(@PathVariable String id) {
        return movieService.findById(id);
    }

    @GetMapping
    Flux<Movie> findAll() {
        return movieService.findAll();
    }

}

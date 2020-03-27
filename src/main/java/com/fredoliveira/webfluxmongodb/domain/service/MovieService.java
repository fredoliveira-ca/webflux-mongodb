package com.fredoliveira.webfluxmongodb.domain.service;

import com.fredoliveira.webfluxmongodb.domain.Movie;
import com.fredoliveira.webfluxmongodb.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Flux<MovieEvent> events(String movieId);

    Mono<Movie> findById(String id);

    Flux<Movie> findAll();
}

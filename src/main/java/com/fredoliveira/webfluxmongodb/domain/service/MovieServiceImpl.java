package com.fredoliveira.webfluxmongodb.domain.service;

import com.fredoliveira.webfluxmongodb.data.repository.MovieRepository;
import com.fredoliveira.webfluxmongodb.domain.Movie;
import com.fredoliveira.webfluxmongodb.domain.MovieEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Flux<MovieEvent> events(String movieId) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
            movieEventSynchronousSink.next(new MovieEvent(movieId, LocalDate.now()));
        }).delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Mono<Movie> findById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> findAll() {
        return movieRepository.findAll();
    }
}

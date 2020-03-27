package com.fredoliveira.webfluxmongodb.web.bootstrap;

import com.fredoliveira.webfluxmongodb.data.repository.MovieRepository;
import com.fredoliveira.webfluxmongodb.domain.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class Bootstrap implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public Bootstrap(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        movieRepository.deleteAll().thenMany(
                Flux.just("Movie 1", "Movie 2", "Movie 3", "Movie 4", "Movie 5", "Movie 6", "Movie 7")
                        .map(Movie::new)
                        .flatMap(movieRepository::save))
            .subscribe(null, null, () -> movieRepository.findAll().subscribe(System.out::println));

    }
}

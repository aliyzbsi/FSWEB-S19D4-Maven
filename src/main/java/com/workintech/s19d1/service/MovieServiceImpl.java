package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.repository.MovieRepository;
import com.workintech.s19d1.validations.MovieValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        Optional<Movie> movie=movieRepository.findById(id);
        if (movie.isPresent()){
            return movie.get();
        }else {
            throw new ApiException("Movie is not found with id: " +id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Movie save(Movie movie) {
        MovieValidations.movieNullCheck(movie);

        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie movieToBeUpdate=findById(id);
        movieToBeUpdate.setName(movie.getName());
        movieToBeUpdate.setRating(movie.getRating());
        movieToBeUpdate.setReleaseDate(movie.getReleaseDate());
        movieToBeUpdate.setDirectorName(movie.getDirectorName());
        movieToBeUpdate.setActors(movie.getActors());

        movieRepository.save(movieToBeUpdate);

        return movieToBeUpdate;
    }

    @Override
    public Movie delete(Movie movie) {
        movieRepository.delete(movie);
        return movie;
    }
}

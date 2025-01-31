package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> findAll(){
        return movieService.findAll();
    }
    @GetMapping("/{id}")
    public Movie findById(@PathVariable("id") long id){
        try {

        }catch (ApiException exception){
            throw new ApiException("Movie is not found with id: "+id, HttpStatus.NOT_FOUND);
        }
        return movieService.findById(id);
    }
    @PostMapping
    public Movie save(@RequestBody Movie movie){
             return movieService.save(movie);
    }
    @PutMapping("/{id}")
    public Movie update(@PathVariable("id") long id,@RequestBody Movie movie){
        return movieService.update(id,movie);
    }
    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable("id") long id){
        Movie movie=findById(id);
        return movieService.delete(movie);
    }

}

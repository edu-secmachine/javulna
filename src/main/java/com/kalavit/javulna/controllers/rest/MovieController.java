/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.controllers.rest;

import com.kalavit.javulna.dto.MovieDto;
import com.kalavit.javulna.model.Movie;
import com.kalavit.javulna.services.MovieService;
import com.kalavit.javulna.services.autodao.MovieAutoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peti
 */
@RestController()
public class MovieController {
    
    @Autowired
    MovieService movieService;
    
    @Autowired
    MovieAutoDao movieAutoDao;
    
    @PostMapping("rest/movie")
    public Movie createMovie(@RequestBody MovieDto md){
        Movie m = new Movie();
        md.toMovie(m);
        movieAutoDao.save(m);
        return m;
    }
    
    @PostMapping("rest/moviexml")
    public Movie createMovie(
            @RequestParam(name = "inputxml") String inputXml){
        Movie m = movieService.saveMovieFromXml(inputXml);
        return m;
    }
    
    @GetMapping("rest/movie")
    public @ResponseBody List<MovieDto> findMovies(
            @RequestParam(required = false) String title, 
            @RequestParam(required = false) String description, 
            @RequestParam(required = false) String genre, 
            @RequestParam(required = false) String id){
        return movieService.findMovie(title, description, genre, id);
    }
}

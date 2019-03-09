/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.controllers;

import com.kalavit.javulna.dto.MovieDto;
import com.kalavit.javulna.services.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author peti
 */
@Controller(value = "movie_no_rest_controller")
public class MovieController {
    
    @Autowired
    MovieService ms;
    
    @GetMapping(path = {"/", "/movies"})
    public String showMovieList(Model model){
        List<MovieDto> movies = ms.findAllMovies();
        model.addAttribute("movies", movies);
        return "movieList";
    }
    
    @GetMapping("/addMovie")
    public String showCreateMoviePage(@ModelAttribute(name = "movie") MovieDto movieDto){
        return "newMovie";
    }
    
    @PostMapping("/addMovie")
    public String createMove(@ModelAttribute(name = "movie") MovieDto movieDto){
        ms.createMovie(movieDto);
        return "redirect:/";
    }
    
}

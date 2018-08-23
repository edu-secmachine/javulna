/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.controllers.rest;

import com.kalavit.javulna.dto.OrderListDto;
import com.kalavit.javulna.dto.OrderResultDto;
import com.kalavit.javulna.exception.InvalidOrderException;
import com.kalavit.javulna.model.MovieObject;
import com.kalavit.javulna.services.SellMovieObjectsService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peti
 */
@RestController
public class SellMovieObjectsController {
    
    @Autowired
    SellMovieObjectsService movieObjectsService;
    
    @GetMapping(path = "rest/movieobject")
    public List<MovieObject> findAllBuyableObjects(){
        return movieObjectsService.findAllBuyableObjects();
    }
    
    @PutMapping(path = "rest/order")
    public OrderResultDto placeOrder(@Valid @RequestBody OrderListDto orderList, BindingResult br){
        if(br.hasErrors()){
            throw new InvalidOrderException(br.getAllErrors());
        }
        return movieObjectsService.placeOrder(orderList);
        
    }
    
}

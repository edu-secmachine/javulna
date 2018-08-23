/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author peti
 */
public class OrderItemDto {
    
    @NotBlank
    private String movieObjectId;
    
    @Min(1)
    @Max(100_000)
    @NotNull
    private Integer nrOfItemsOrdered;

    public String getMovieObjectId() {
        return movieObjectId;
    }

    public void setMovieObjectId(String movieObjectId) {
        this.movieObjectId = movieObjectId;
    }

    public Integer getNrOfItemsOrdered() {
        return nrOfItemsOrdered;
    }

    public void setNrOfItemsOrdered(Integer nrOfItemsOrdered) {
        this.nrOfItemsOrdered = nrOfItemsOrdered;
    }
    
    
    
}

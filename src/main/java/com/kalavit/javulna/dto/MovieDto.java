/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.dto;

import com.kalavit.javulna.model.Movie;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peti
 */
@XmlRootElement(name = "createMovie")
public class MovieDto {
    @XmlElement
    private String id;
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private String genre;
    @XmlElement
    private String imageUrl;

    public MovieDto() {
    }
    
    public MovieDto(Movie m) {
        this.title = m.getTitle();
        this.genre = m.getGenre();
        this.description = m.getDescription();
        this.imageUrl = m.getImageUrl();
    }

    public MovieDto(String title, String description, String genre) {
        this.title = title;
        this.description = description;
        this.genre = genre;
    }
    
    public void toMovie(Movie m){
        if(this.id != null){
            m.setId(id);
        }
        m.setDescription(description);
        m.setGenre(genre);
        m.setTitle(title);
        m.setImageUrl(imageUrl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
   
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
}

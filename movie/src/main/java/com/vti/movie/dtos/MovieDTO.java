package com.vti.movie.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieDTO {
    private int id;
    private String name;
    private String smallImageURl;
    private String shortDescription;
    private String longDescription;
    private String largeImageURL;
    private String director;
    private String actors;
    private String categories;
    private LocalDateTime releaseDate;
    private int duration;
    private String trailerURL;
    private String language;
    private String rated;
}
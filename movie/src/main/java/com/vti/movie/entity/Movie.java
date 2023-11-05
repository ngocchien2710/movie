package com.vti.movie.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name ="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name="name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name= "imgURL", length = 500, nullable = false)
    private String imgURL;

    @Column(name = "director", length = 100, nullable = false)
    private String director;

    @Column(name ="description", length = 1000, nullable = false)
    private String description;

    @Column(name= "actors", length = 500, nullable = false)
    private String actors;

    @Column(name = "categories", length = 100, nullable = false)
    private String categories;

    @Column(name = "release_Date" , nullable = false)
    private LocalDate releaseDate;

    @Column(name = "duration", nullable = false, length = 1000)
    private int duration;

    @Column(name ="format", nullable = false)
    private String format;

    @Column(name = "trailerURL", length = 500, nullable = false)
    private String trailerURL;

    @Column(name = "nation_producter", nullable = false, length = 100)
    private String nationProducter;

    @Column(name = "rated", unique = true )
    private int rated;
//    private int isShowing;
}

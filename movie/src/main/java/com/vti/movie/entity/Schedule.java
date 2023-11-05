package com.vti.movie.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name ="schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;

    @Column(name ="movie_date", nullable = false)
    private LocalDate movieDate;

    @Column(name ="start_time", nullable = false)
    private LocalTime startTime;

    @Column(name= "finish_time", nullable = false)

    private  LocalTime finishTime;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}

package com.vti.movie.controllers;

import com.vti.movie.dtos.CinemaDTO;
import com.vti.movie.entity.Cinema;
import com.vti.movie.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/cinemas")
public class CinemaController {
    @Autowired
    private ICinemaService cinemaService;

    @GetMapping
    private ResponseEntity<List<CinemaDTO>> getCinemaesThatShowTheMovie(@RequestParam Integer movieId){
        return new ResponseEntity(cinemaService.getCinemaesThatShowTheMovie(movieId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        return new ResponseEntity<>(cinemas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable int id) {
        Cinema cinema = cinemaService.getCinemaById(id);
        if (cinema != null) {
            return new ResponseEntity<>(cinema, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Cinema> createCinema(@RequestBody Cinema cinema) {
        Cinema createdCinema = cinemaService.createCinema(cinema);
        return new ResponseEntity<>(createdCinema, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cinema> updateCinema(@PathVariable int id, @RequestBody Cinema updatedCinema) {
        Cinema cinema = cinemaService.updateCinema(id, updatedCinema);
        if (cinema != null) {
            return new ResponseEntity<>(cinema, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable int id) {
        cinemaService.deleteCinema(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

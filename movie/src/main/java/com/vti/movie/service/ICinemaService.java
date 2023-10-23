package com.vti.movie.service;

import com.vti.movie.dtos.CinemaDTO;
import com.vti.movie.entity.Cinema;

import java.util.List;

public interface ICinemaService {
    List<CinemaDTO> getCinemaesThatShowTheMovie(Integer movieId);

    List<Cinema> getAllCinemas();

    Cinema getCinemaById(int id);

    Cinema createCinema(Cinema cinema);

    Cinema updateCinema(int id, Cinema updatedCinema);

    void deleteCinema(int id);
}

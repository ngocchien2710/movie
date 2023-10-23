package com.vti.movie.service;

import com.vti.movie.dtos.MovieDTO;
import com.vti.movie.entity.Movie;

import java.util.List;

public interface IMovieService {
    List<MovieDTO> findAllShowingMovies();

    MovieDTO getById(Integer movieId);

    List<MovieDTO> findAllShowingMoviesByName(String keyword);

    Movie createMovie(Movie movie);

    Movie updateMovie(int id, Movie updatedMovie);

    void deleteMovie(int id);
}

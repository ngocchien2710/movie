package com.vti.movie.repository;

import com.vti.movie.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICinemaRepository extends JpaRepository<Cinema, Integer> {
    @Query("SELECT b FROM Cinema b where b.id in " +
            "(SELECT s.cinema.id FROM Schedule s JOIN s.movie m WHERE s.movie.id = :movieId )")
    List<Cinema> getCinemaThatShowTheMovie(@Param("movieId") Integer movieId);
}


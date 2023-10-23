package com.vti.movie.repository;

import com.vti.movie.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE r.id in (SELECT s.room.id FROM Schedule s WHERE s.movie.id=:movieId AND " +
            "s.cinema.id = :cinemaId AND s.startDate=:startDate AND s.startTime=:startTime)")
    List<Room> getRoomByCinemaAndMovieAndSchedule(@Param("movieId") Integer movieId,
                                                  @Param("cinemaId") Integer cinemaId,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("startTime") LocalTime startTime);
}

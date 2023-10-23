package com.vti.movie.repository;

import com.vti.movie.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query("SELECT DISTINCT s.startTime FROM Schedule s WHERE s.movie.id=:movieId AND s.cinema.id" +
            "= :cinemaId AND s.startDate=:startDate")
    List<LocalTime> getStartTimeByMovie_IdAndCinema_IdAndStartDate(@Param("movieId") Integer movieId
            , @Param("cinemaId") Integer cinemaId
            , @Param("startDate") LocalDate startDate);

    List<Schedule> getSchedulesByMovie_IdAndCinema_IdAndStartDateAndStartTimeAndRoom_Id(Integer movieId,Integer cinemaId
            , LocalDate startDate,LocalTime startTime,Integer roomId);
}

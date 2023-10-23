package com.vti.movie.service;

import com.vti.movie.dtos.SeatDTO;
import com.vti.movie.entity.Seat;

import java.util.List;

public interface ISeatService {
    List<SeatDTO> getSeatsByScheduleId(Integer scheduleId);

    List<Seat> getAllSeats();

    Seat getSeatById(int id);

    Seat createSeat(Seat seat);

    Seat updateSeat(int id, Seat updatedSeat);

    void deleteSeat(int id);
}

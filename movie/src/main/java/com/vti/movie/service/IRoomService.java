package com.vti.movie.service;

import com.vti.movie.dtos.RoomDTO;
import com.vti.movie.entity.Room;

import java.util.List;

public interface IRoomService {
    List<RoomDTO> getRooms(Integer movieId, Integer cinemaId, String startDate, String startTime);

    Room createRoom(Room room);

    Room updateRoom(int id, Room updatedRoom);

    void deleteRoom(int id);
}

package com.vti.movie.service;

import com.vti.movie.dtos.SeatDTO;
import com.vti.movie.entity.Room;
import com.vti.movie.entity.Seat;
import com.vti.movie.repository.IScheduleRepository;
import com.vti.movie.repository.ISeatRepository;
import com.vti.movie.repository.ITicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService implements ISeatService {

    private final ISeatRepository seatRepository;

    private final IScheduleRepository scheduleRepository;

    private final ITicketRepository ticketRepository;

    private final ModelMapper modelMapper;
    @Autowired
    public SeatService(ISeatRepository seatRepository, IScheduleRepository scheduleRepository, ITicketRepository ticketRepository){
        this.modelMapper = new ModelMapper();
        this.seatRepository = seatRepository;
        this.scheduleRepository =  scheduleRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<SeatDTO> getSeatsByScheduleId(Integer scheduleId) {
        // Lấy ra các chỗ ngồi của phòng trong lịch đó
        Room room = scheduleRepository.getById(scheduleId).getRoom();
        List<Seat> listSeat = seatRepository.getSeatByRoom_Id(room.getId());

        // Lấy ra các vé đã được đặt trong lịch đó rồi map sang các chỗ ngồi
        List<Seat> occupiedSeats = ticketRepository.findTicketsBySchedule_Id(scheduleId)
                .stream().map(ticket -> ticket.getSeat())
                .collect(Collectors.toList());

        // Map list chỗ ngồi của phòng ở bước 1 sang list dto
        List<SeatDTO> filteredSeats = listSeat.stream().map(seat -> {
            SeatDTO seatDTO = modelMapper.map(seat,SeatDTO.class);
            if(occupiedSeats.stream()
                    .map(occupiedSeat->occupiedSeat.getId())
                    .collect(Collectors.toList()).contains(seat.getId())){
                seatDTO.setIsOccupied(1); // Nếu ghế nào nằm trong list ghế đã được occupied thì set = 1
            }
            return seatDTO;
        }).collect(Collectors.toList());

        return  filteredSeats;
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeatById(int id) {
        return seatRepository.findById(id).orElse(null);
    }

    @Override
    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Seat updateSeat(int id, Seat updatedSeat) {
        Seat existingSeat = seatRepository.findById(id).orElse(null);
        if (existingSeat != null) {
            existingSeat.setName(updatedSeat.getName());
            existingSeat.setRoom(updatedSeat.getRoom());
            return seatRepository.save(existingSeat);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSeat(int id) {
        seatRepository.deleteById(id);
    }
}

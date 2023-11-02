package com.vti.movie.service;

import com.vti.movie.dtos.ScheduleDTO;
import com.vti.movie.entity.Schedule;
import com.vti.movie.repository.IScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository scheduleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ScheduleService(IScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = new ModelMapper();
    }
    @Override
    public List<String> getStartTimes(Integer movieId, Integer cinemaId, LocalDate startDate) {
        return scheduleRepository.getStartTimeByMovie_IdAndCinema_IdAndStartDate(movieId,cinemaId,startDate)
                .stream().map(localTime -> localTime.format(DateTimeFormatter.ofPattern("HH:mm")))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> getSchedules(Integer movieId, Integer cinemaId, String startDate, String startTime, Integer roomId) {
        return scheduleRepository.getSchedulesByMovie_IdAndCinema_IdAndStartDateAndStartTimeAndRoom_Id(movieId,cinemaId
                        ,LocalDate.parse(startDate), LocalTime.parse(startTime), roomId)
                .stream().map(schedule -> modelMapper.map(schedule,ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(int id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(int id, Schedule updatedSchedule) {
        Schedule existingSchedule = scheduleRepository.findById(id).orElse(null);
        if (existingSchedule != null) {
            existingSchedule.setStartDate(updatedSchedule.getStartDate());
            existingSchedule.setStartTime(updatedSchedule.getStartTime());
            existingSchedule.setPrice(updatedSchedule.getPrice());
            existingSchedule.setMovie(updatedSchedule.getMovie());
            existingSchedule.setCinema(updatedSchedule.getCinema());
            existingSchedule.setRoom(updatedSchedule.getRoom());
            return scheduleRepository.save(existingSchedule);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSchedule(int id) {
        scheduleRepository.deleteById(id);
    }
}

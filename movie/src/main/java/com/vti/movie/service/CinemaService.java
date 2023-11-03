package com.vti.movie.service;

import com.vti.movie.dtos.CinemaDTO;
import com.vti.movie.entity.Cinema;
import com.vti.movie.repository.ICinemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaService implements ICinemaService {

    private final ICinemaRepository cinemaRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CinemaService( ICinemaRepository cinemaRepository) {
        this.modelMapper = new ModelMapper();

            this.cinemaRepository = cinemaRepository;

    }

    @Override
    public List<CinemaDTO> getCinemaesThatShowTheMovie(Integer movieId) {
        return cinemaRepository.getCinemaThatShowTheMovie(movieId)
                .stream().map(cinema -> modelMapper.map(cinema,CinemaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema getCinemaById(int id) {
        return cinemaRepository.findById(id).orElse(null);
    }

    @Override
    public Cinema createCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema updateCinema(int id, Cinema updatedCinema) {
        Cinema existingCinema = cinemaRepository.findById(id).orElse(null);
        if (existingCinema != null) {
            existingCinema.setName(updatedCinema.getName());
            existingCinema.setDiaChi(updatedCinema.getDiaChi());

            existingCinema.setImgURL(updatedCinema.getImgURL());
            return cinemaRepository.save(existingCinema);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCinema(int id) {
        cinemaRepository.deleteById(id);
    }
}

package com.vti.movie.service;

import com.vti.movie.dtos.TicketDTO;
import com.vti.movie.entity.Ticket;
import com.vti.movie.repository.ITicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<TicketDTO> getTicketsByUserId(Integer userId) {
        return ticketRepository.findTicketsByUserId(userId)
                .stream().map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(int id, Ticket updatedTicket) {
        Ticket existingTicket = ticketRepository.findById(id).orElse(null);
        if (existingTicket != null) {
            existingTicket.setQrImageURL(updatedTicket.getQrImageURL());
            existingTicket.setSeat(updatedTicket.getSeat());
            existingTicket.setSchedule(updatedTicket.getSchedule());
            existingTicket.setBill(updatedTicket.getBill());
            return ticketRepository.save(existingTicket);
        } else {
            return null;
        }
    }

    @Override
    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }
}

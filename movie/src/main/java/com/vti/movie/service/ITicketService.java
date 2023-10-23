package com.vti.movie.service;

import com.vti.movie.dtos.TicketDTO;
import com.vti.movie.entity.Ticket;

import java.util.List;

public interface ITicketService {
    List<TicketDTO> getTicketsByUserId(Integer userId);

    List<Ticket> getAllTickets();

    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(int id, Ticket updatedTicket);

    void deleteTicket(int id);
}

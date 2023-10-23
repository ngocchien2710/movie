package com.vti.movie.dtos;

import com.vti.movie.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BillDTO {
    private int id;
    private LocalDateTime createdTime;
    private List<TicketDTO> listTickets;
    private User user;
}

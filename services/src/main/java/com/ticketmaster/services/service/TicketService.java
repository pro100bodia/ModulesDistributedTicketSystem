package com.ticketmaster.services.service;

import com.ticketmaster.services.persistence.repository.jpa.TicketJpaModelRepository;
import com.ticketmaster.services.service.model.TicketModel;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private TicketJpaModelRepository ticketRepo;

    public TicketService(TicketJpaModelRepository ticketRepository) {
        this.ticketRepo = ticketRepository;
    }

    public TicketModel findById(Long id) {
        return ticketRepo.findById(id);
    }
}

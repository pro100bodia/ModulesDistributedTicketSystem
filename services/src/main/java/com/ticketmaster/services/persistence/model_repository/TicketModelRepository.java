package com.ticketmaster.services.persistence.model_repository;

import com.ticketmaster.services.persistence.entity.Ticket;
import com.ticketmaster.services.persistence.repository.TicketRepository;
import com.ticketmaster.services.service.model.TicketModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TicketModelRepository {
    private TicketRepository ticketRepo;
    private final ModelMapper modelMapper;

    public TicketModelRepository(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepo = ticketRepository;
        this.modelMapper = modelMapper;
    }

    public TicketModel findById(Long id) {
        Ticket ticket = ticketRepo.findById(id).orElse(null);

        return modelMapper.map(ticket, TicketModel.class);
    }
}

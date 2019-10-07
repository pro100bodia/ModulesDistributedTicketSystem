package com.ticketmaster.persistence.repository.jpa;

import com.ticketmaster.persistence.entity.Ticket;
import com.ticketmaster.persistence.repository.TicketRepository;
import com.ticketmaster.service.model.TicketModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TicketJpaModelRepository implements TicketRepository {
    private TicketEntityRepository ticketRepo;
    private final ModelMapper modelMapper;

    public TicketJpaModelRepository(TicketEntityRepository ticketEntityRepository, ModelMapper modelMapper) {
        this.ticketRepo = ticketEntityRepository;
        this.modelMapper = modelMapper;
    }

    public TicketModel findById(Long id) {
        Ticket ticket = ticketRepo.findById(id).orElse(null);

        return modelMapper.map(ticket, TicketModel.class);
    }
}

package com.ticketmaster.services.service;

import com.ticketmaster.services.persistence.model_repository.TicketJpaModelRepository;
import com.ticketmaster.services.service.model.TicketModel;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private TicketJpaModelRepository ticketRepo;

    public TicketService(TicketJpaModelRepository ticketRepository) {
        this.ticketRepo = ticketRepository;
    }

//    public List<TicketDto> getAllTickets() throws NotFoundException {
////        return ticketRepository.findAll();
////        if (result == null)
////            throw new NotFoundException("Database does not contain any ticket");
//
////        return result;
//    }

    public TicketModel findById(Long id) {

        return ticketRepo.findById(id);
    }
}

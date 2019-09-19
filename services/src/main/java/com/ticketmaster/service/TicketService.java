package com.ticketmaster.service;

import com.ticketmaster.persistence.model_repository.TicketModelRepository;
import com.ticketmaster.service.model.TicketModel;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private TicketModelRepository ticketRepo;

    public TicketService(TicketModelRepository ticketRepository) {
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

        TicketModel ticketModel = ticketRepo.findById(id);
        return ticketModel;
    }
}

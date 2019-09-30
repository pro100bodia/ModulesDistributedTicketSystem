package com.ticketmaster.services.persistence.repository;

import com.ticketmaster.services.service.model.TicketModel;

public interface TicketRepository {
    TicketModel findById(Long id);
}

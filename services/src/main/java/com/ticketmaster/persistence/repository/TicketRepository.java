package com.ticketmaster.persistence.repository;

import com.ticketmaster.service.model.TicketModel;

public interface TicketRepository {
    TicketModel findById(Long id);
}

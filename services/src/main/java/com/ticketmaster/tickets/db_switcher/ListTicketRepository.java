package com.ticketmaster.tickets.db_switcher;

import com.ticketmaster.model.TicketModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListTicketRepository {
    List<TicketModel> findAll();
}

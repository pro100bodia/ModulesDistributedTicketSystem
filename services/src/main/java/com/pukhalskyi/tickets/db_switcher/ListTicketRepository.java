package com.pukhalskyi.tickets.db_switcher;

import com.pukhalskyi.model.TicketModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListTicketRepository {
    List<TicketModel> findAll();
}

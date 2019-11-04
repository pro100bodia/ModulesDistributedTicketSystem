package com.ticketmaster.tickets.db_switcher;

import com.ticketmaster.entity.DbType;
import com.ticketmaster.model.TicketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TicketService {

    private Map<String, com.ticketmaster.tickets.db_switcher.ListTicketRepository> ticketRepoMap;

    @Autowired
    public TicketService(Map<String, ListTicketRepository> ticketRepoMap) {
        this.ticketRepoMap = ticketRepoMap;
    }

    public List<TicketModel> findAll(DbType db) {
        return ticketRepoMap.get(db.getListRepo()).findAll();
    }
}

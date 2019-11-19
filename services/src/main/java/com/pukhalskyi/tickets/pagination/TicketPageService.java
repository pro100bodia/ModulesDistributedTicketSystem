package com.pukhalskyi.tickets.pagination;

import com.pukhalskyi.entity.DbType;
import com.pukhalskyi.model.TicketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TicketPageService {

    private Map<String, TicketPageRepository> ticketRepoMap;

    @Autowired
    public TicketPageService(Map<String, TicketPageRepository> ticketRepoMap) {
        this.ticketRepoMap = ticketRepoMap;
    }

    public Page<TicketModel> findAll(DbType db, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return ticketRepoMap.get(db.getPageRepo()).findAll(pageable);
    }
}

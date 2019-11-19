package com.pukhalskyi.tickets.pagination;

import com.pukhalskyi.model.TicketModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPageRepository {
    Page<TicketModel> findAll(Pageable pageable);
}

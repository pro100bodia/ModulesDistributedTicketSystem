package com.pukhalskyi.tickets.pagination.jpa;

import com.pukhalskyi.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketPageEntityRepository extends PagingAndSortingRepository<Ticket, Long> {
    Page<Ticket> findAll(Pageable pageable);
}

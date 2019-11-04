package com.ticketmaster.tickets.pagination.jpa;

import com.ticketmaster.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketPageEntityRepository extends PagingAndSortingRepository<Ticket, Long> {
    Page<Ticket> findAll(Pageable pageable);
}

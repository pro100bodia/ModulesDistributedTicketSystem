package com.ticketmaster.persistence.repository.jpa;

import com.ticketmaster.persistence.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketEntityRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAll();

    Optional<Ticket> findById(Long id);
}

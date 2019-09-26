package com.ticketmaster.services.persistence.model_repository;

import com.ticketmaster.services.persistence.TicketRepository;
import com.ticketmaster.services.service.model.TicketModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketJdbcRepository implements TicketRepository {
    private JdbcTemplate jdbcTemplate;

    public TicketJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TicketModel findById(Long id) {
        return jdbcTemplate.queryForObject(String.format("SELECT * FROM user WHERE username = %d", id), TicketModel.class);
    }
}

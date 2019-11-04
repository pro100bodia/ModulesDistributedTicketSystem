package com.ticketmaster.tickets.pagination.jdbc;

import com.ticketmaster.model.TicketModel;
import com.ticketmaster.tickets.pagination.TicketPageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TicketPageJdbcModelRepository implements TicketPageRepository {
    private JdbcTemplate jdbcTemplate;

    public TicketPageJdbcModelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<TicketModel> findAll(Pageable pageable) {
        int limit = pageable.getPageSize();
        long offset = pageable.getOffset();

        return new PageImpl<>(jdbcTemplate.query(String.format("SELECT * FROM ticket LIMIT %d OFFSET %d",
                limit, offset), this::mapRowToTicket));
    }

    private TicketModel mapRowToTicket(ResultSet rs, int rowNum) throws SQLException {
        return new TicketModel(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}

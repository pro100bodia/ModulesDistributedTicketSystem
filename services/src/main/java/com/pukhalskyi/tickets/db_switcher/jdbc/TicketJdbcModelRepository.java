package com.pukhalskyi.tickets.db_switcher.jdbc;

import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.tickets.db_switcher.ListTicketRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TicketJdbcModelRepository implements ListTicketRepository {
    private JdbcTemplate jdbcTemplate;

    public TicketJdbcModelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TicketModel> findAll() {
        return jdbcTemplate.query("SELECT * FROM ticket", this::mapRowToUser);
    }

    private TicketModel mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new TicketModel(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}

package com.ticketmaster.users.pagination.jdbc;

import com.ticketmaster.model.UserModel;
import com.ticketmaster.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcPaginatedUserModelRepository implements UserRepository {
    private JdbcTemplate jdbcTemplate;
    private int page, size;

    @Autowired
    public JdbcPaginatedUserModelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.page = 0;
        this.size = 3;
    }


    public JdbcPaginatedUserModelRepository(JdbcTemplate jdbcTemplate, int page, int size) {
        this.jdbcTemplate = jdbcTemplate;
        this.page = page;
        this.size = size;
    }

    @Override
    public Page<UserModel> findAll() {
        int limit = size;
        long offset = page * size;

        return new PageImpl<>(jdbcTemplate.query(String.format("SELECT * FROM user LIMIT %d OFFSET %d", limit, offset),
                this::mapRowToUser));
    }

    private UserModel mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new UserModel(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email")
        );
    }

}

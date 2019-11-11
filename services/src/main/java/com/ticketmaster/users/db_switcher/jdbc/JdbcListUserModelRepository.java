package com.ticketmaster.users.db_switcher.jdbc;

import com.ticketmaster.model.UserModel;
import com.ticketmaster.users.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcListUserModelRepository implements UserRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcListUserModelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserModel> findAll() {
        return jdbcTemplate.query("SELECT * FROM user", this::mapRowToUser);
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

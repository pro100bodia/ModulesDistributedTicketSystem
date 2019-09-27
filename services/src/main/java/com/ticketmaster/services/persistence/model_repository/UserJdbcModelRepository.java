package com.ticketmaster.services.persistence.model_repository;

import com.ticketmaster.services.persistence.DataType;
import com.ticketmaster.services.persistence.UserRepository;
import com.ticketmaster.services.persistence.entity.Role;
import com.ticketmaster.services.service.model.TicketModel;
import com.ticketmaster.services.service.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJdbcModelRepository implements UserRepository {
    private JdbcTemplate jdbcTemplate;
    private static final DataType type = DataType.MYSQL;

    public UserJdbcModelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DataType getType() {
        return type;
    }

    @Override
    public List<UserModel> findAll() {
        //TODO: add tickets
        return jdbcTemplate.query("SELECT * FROM user", this::mapRowToUser);
    }

    @Override
    public UserModel findByUsername(String username) {
        return jdbcTemplate.queryForObject(String.format("SELECT * FROM user WHERE username = %s", username), UserModel.class);
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        return jdbcTemplate.queryForObject(
                String.format("INSERT INTO user (username, password, first_name, last_name, email, role) VALUES\n" +
                                "(%s, %s, %s, %s, %s, %s)",
                        userModel.getUsername(),
                        userModel.getUsername(),
                        userModel.getUsername(),
                        userModel.getUsername(),
                        userModel.getUsername(),
                        userModel.getRole().toString()
                ), UserModel.class);
    }

    @Override
    public void deleteUser(String username) {
        UserModel user = findByUsername(username);

        for (TicketModel ticket : user.getTickets())
            jdbcTemplate.execute(String.format("DELETE * FROM ticket_user WHERE %d", user.getId()));

        jdbcTemplate.execute(String.format("DELETE * FROM user WHERE %d", user.getId()));
    }

    private UserModel mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new UserModel(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                Role.valueOf(rs.getString("role")),
                null);
    }

//    private TicketModel getRawTickets(Long userId){
//        return jdbcTemplate.query("SELECT * FROM ");
//    }
}

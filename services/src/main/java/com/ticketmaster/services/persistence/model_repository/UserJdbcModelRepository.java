package com.ticketmaster.services.persistence.model_repository;

import com.ticketmaster.services.persistence.UserRepository;
import com.ticketmaster.services.service.model.TicketModel;
import com.ticketmaster.services.service.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcModelRepository implements UserRepository {
    private JdbcTemplate jdbcTemplate;

    public UserJdbcModelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserModel> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM user", UserModel.class);
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
}

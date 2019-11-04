package com.ticketmaster.config;

import com.ticketmaster.users.UserRepository;
import com.ticketmaster.users.db_switcher.jdbc.JdbcListUserModelRepository;
import com.ticketmaster.users.db_switcher.jpa.JpaListUserModelRepository;
import com.ticketmaster.users.db_switcher.jpa.UserEntityRepository;
import com.ticketmaster.users.pagination.jdbc.JdbcPaginatedUserModelRepository;
import com.ticketmaster.users.pagination.jpa.JpaPaginatedUserModelRepository;
import com.ticketmaster.users.pagination.jpa.PaginatedUserEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Configuration
@EnableAutoConfiguration
public class ServiceConfig {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PaginatedUserEntityRepository paginatedUserEntityRepository;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserRepository userRepository(HttpServletRequest request) {
        String version = Optional.ofNullable(request.getHeader("version")).orElse("latest");
        String database = Optional.ofNullable(request.getHeader("db")).orElse("h2");

        if (version.equals("1.1")) {
            return (database.equals("mysql")) ? new JdbcListUserModelRepository(jdbcTemplate) :
                    new JpaListUserModelRepository(userEntityRepository, modelMapper);
        }

        int page = Integer.parseInt(Optional.ofNullable(request.getHeader("page")).orElse("0"));
        int size = Integer.parseInt(Optional.ofNullable(request.getHeader("size")).orElse("3"));

        return ("mysql".equalsIgnoreCase(database) ? new JdbcPaginatedUserModelRepository(jdbcTemplate, page, size) :
                new JpaPaginatedUserModelRepository(paginatedUserEntityRepository, modelMapper, page, size));

    }


}

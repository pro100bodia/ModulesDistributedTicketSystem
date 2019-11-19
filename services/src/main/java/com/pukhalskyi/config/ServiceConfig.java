package com.pukhalskyi.config;

import com.pukhalskyi.users.UserService;
import com.pukhalskyi.users.db_switcher.jdbc.JdbcListUserModelRepository;
import com.pukhalskyi.users.db_switcher.jpa.JpaListUserModelRepository;
import com.pukhalskyi.users.pagination.jdbc.JdbcPaginatedUserModelRepository;
import com.pukhalskyi.users.pagination.jpa.JpaPaginatedUserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Configuration
@EnableAutoConfiguration
public class ServiceConfig {

    @Autowired
    private JpaListUserModelRepository jpaListUserModelRepository;

    @Autowired
    private JdbcListUserModelRepository jdbcListUserModelRepository;

    @Autowired
    private JpaPaginatedUserModelRepository jpaPaginatedUserModelRepository;

    @Autowired
    private JdbcPaginatedUserModelRepository jdbcPaginatedUserModelRepository;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserService userService(HttpServletRequest request) {
        String version = Optional.ofNullable(request.getHeader("version")).orElse("latest");
        String database = Optional.ofNullable(request.getHeader("db")).orElse("h2");

        if (version.equals("1.1")) {
            return "mysql".equalsIgnoreCase(database) ? new UserService(jdbcListUserModelRepository) :
                    new UserService(jpaListUserModelRepository);
        }

        int page = Integer.parseInt(Optional.ofNullable(request.getHeader("page")).orElse("0"));
        int size = Integer.parseInt(Optional.ofNullable(request.getHeader("size")).orElse("3"));

        if ("mysql".equalsIgnoreCase(database)) {
            jdbcPaginatedUserModelRepository.setPage(page);
            jdbcPaginatedUserModelRepository.setSize(size);
            return new UserService(jdbcPaginatedUserModelRepository);
        }
        else {
            jpaPaginatedUserModelRepository.setPage(page);
            jpaPaginatedUserModelRepository.setSize(size);
            return new UserService(jpaPaginatedUserModelRepository);
        }

    }
}

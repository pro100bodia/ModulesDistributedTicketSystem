package com.ticketmaster.persistence.repository.jpa;

import com.ticketmaster.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User save(User user);
}

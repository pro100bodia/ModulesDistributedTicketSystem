package com.ticketmaster.users.db_switcher.jpa;

import com.ticketmaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//
//    User save(User user);
}

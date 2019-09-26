package com.ticketmaster.data.repository;

import com.ticketmaster.services.Application;
import com.ticketmaster.services.persistence.entity.User;
import com.ticketmaster.services.persistence.repository.UserEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserJpaModelRepositoryItTest {

    @Autowired
    private UserEntityRepository subject;

    @Test
    public void givenUsername_whenFindByUsername_thenReturnUser() {
        //given
        String username = "bohdanpukhalskyi";
        User user = new User();

        //when
//        Optional<User> result = this.subject.findByUsername(username);

        //then
//        assertEquals(Optional.of(user), result);
    }
}

package com.ticketmaster.users;

import org.springframework.stereotype.Service;

@Service
public class UserService<T extends Iterable> {
    private UserRepository userRepository;

    public UserService(UserRepository<T> userRepository) {
        this.userRepository = userRepository;
    }

    T findAll() {
        return (T) userRepository.findAll();
    }
}

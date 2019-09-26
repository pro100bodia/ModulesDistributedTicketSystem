package com.ticketmaster.services.persistence;

import com.ticketmaster.services.service.model.UserModel;

import java.util.List;

public interface UserRepository {
    List<UserModel> findAll();

    UserModel findByUsername(String username);

    UserModel saveUser(UserModel userModel);

    void deleteUser(String username);
}

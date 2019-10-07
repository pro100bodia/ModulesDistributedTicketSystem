package com.ticketmaster.persistence.repository;

import com.ticketmaster.service.model.UserModel;

import java.util.List;

public interface UserRepository {
    List<UserModel> findAll();

    UserModel findByUsername(String username);

    UserModel saveUser(UserModel userModel);

    void deleteUser(String username);

    DataType getType();

    default boolean supportType(DataType type) {
        return type.equals(getType());
    }
}

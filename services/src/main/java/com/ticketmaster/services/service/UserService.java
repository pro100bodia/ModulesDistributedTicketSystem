package com.ticketmaster.services.service;

import com.ticketmaster.services.exceptions.NotFoundException;
import com.ticketmaster.services.persistence.model_repository.UserJpaModelRepository;
import com.ticketmaster.services.service.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserJpaModelRepository userRepo;

    UserService(UserJpaModelRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserModel> findAll() {
        List<UserModel> userModels = userRepo.findAll();

        if (userModels == null) {
            throw new NotFoundException("Database has no users");
        }

        return userModels;
    }

    public UserModel getUserByUsername(String username) {
        UserModel result = userRepo.findByUsername(username);

        if (result == null) {
            throw new NotFoundException("User not found");
        }

        return result;
    }

    public UserModel saveUser(UserModel userModel) {
        if (userModel == null || userModel.getUsername().equals("")) {
            throw new NotFoundException("Requested user is null");
        }

        return userRepo.saveUser(userModel);
    }


    public void deleteUser(String username) {
        userRepo.deleteUser(username);
    }
}

package com.ticketmaster.services.service;

import com.ticketmaster.services.exceptions.NotFoundException;
import com.ticketmaster.services.persistence.repository.DataType;
import com.ticketmaster.services.persistence.repository.UserRepository;
import com.ticketmaster.services.service.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private List<UserRepository> repositoryList;

    public UserService(List<UserRepository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    public List<UserModel> findAll(DataType db) {
        UserRepository suitableRepo = repositoryList.get(0);

        for (UserRepository userRepo : repositoryList) {
            if (userRepo.supportType(db)) {
                suitableRepo = userRepo;
            }
        }

        List<UserModel> userModels = suitableRepo.findAll();

        if (userModels == null) {
            throw new NotFoundException("Database has no users");
        }

        return userModels;
    }

    public UserModel getUserByUsername(DataType db, String username) {
        UserRepository suitableRepo = repositoryList.get(0);

        for (UserRepository userRepo : repositoryList) {
            if (userRepo.supportType(db)) {
                suitableRepo = userRepo;
            }
        }

        UserModel result = suitableRepo.findByUsername(username);

        if (result == null) {
            throw new NotFoundException("User not found");
        }

        return result;
    }

    public UserModel saveUser(DataType db, UserModel userModel) {
        UserRepository suitableRepo = repositoryList.get(0);

        for (UserRepository userRepo : repositoryList) {
            if (userRepo.supportType(db)) {
                suitableRepo = userRepo;
            }
        }

        return suitableRepo.saveUser(userModel);
    }

    public void deleteUser(DataType db, String username) {
        UserRepository suitableRepo = repositoryList.get(0);

        for (UserRepository userRepo : repositoryList) {
            if (userRepo.supportType(db)) {
                suitableRepo = userRepo;
            }
        }

        suitableRepo.deleteUser(username);
    }
}

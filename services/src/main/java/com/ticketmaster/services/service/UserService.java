package com.ticketmaster.services.service;

import com.ticketmaster.services.exceptions.NotFoundException;
import com.ticketmaster.services.persistence.repository.DataType;
import com.ticketmaster.services.service.model.UserModel;
import com.ticketmaster.services.persistence.repository.UserRepository;
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

//    public UserModel getUserByUsername(String username) {
//        UserModel result = userRepo.findByUsername(username);
//
//        if (result == null) {
//            throw new NotFoundException("User not found");
//        }
//
//        return result;
//    }
//
    public UserModel saveUser(UserModel userModel) {
        
        if (userModel == null || userModel.getUsername().equals("")) {
            throw new NotFoundException("Requested user is null");
        }

        return userRepo.saveUser(userModel);
    }
//
//
//    public void deleteUser(String username) {
//        userRepo.deleteUser(username);
//    }
}

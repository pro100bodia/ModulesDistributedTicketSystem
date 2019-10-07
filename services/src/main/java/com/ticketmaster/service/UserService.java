package com.ticketmaster.service;

import com.ticketmaster.controller.DataTypeConverter;
import com.ticketmaster.exceptions.NotFoundException;
import com.ticketmaster.persistence.repository.DataType;
import com.ticketmaster.persistence.repository.UserRepository;
import com.ticketmaster.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private Map<String, UserRepository> repositoryMap;

    @Autowired
    private DataTypeConverter dataTypeConverter;

    public UserService(Map<String, UserRepository> repositoryMap, DataTypeConverter dataTypeConverter) {
        this.repositoryMap = repositoryMap;
        this.dataTypeConverter = dataTypeConverter;
    }

    public List<UserModel> findAll(String db) {

        if (db.equals("h2")) {
            db = "userJpaModelRepository";
        } else {
            db = "userJdbcModelRepository";
        }
        UserRepository suitableRepo = repositoryMap.get(db);

        List<UserModel> userModels = suitableRepo.findAll();

        if (userModels == null) {
            throw new NotFoundException("Database has no users");
        }

        return userModels;
    }

    public UserModel getUserByUsername(DataType db, String username) {
        UserRepository suitableRepo = repositoryMap.get(dataTypeConverter.convert(db));

        UserModel result = suitableRepo.findByUsername(username);

        if (result == null) {
            throw new NotFoundException("User not found");
        }

        return result;
    }

    public UserModel saveUser(DataType db, UserModel userModel) {
        UserRepository suitableRepo = repositoryMap.get(dataTypeConverter.convert(db));

        return suitableRepo.saveUser(userModel);
    }

    public void deleteUser(DataType db, String username) {
        UserRepository suitableRepo = repositoryMap.get(dataTypeConverter.convert(db));

        suitableRepo.deleteUser(username);
    }
}

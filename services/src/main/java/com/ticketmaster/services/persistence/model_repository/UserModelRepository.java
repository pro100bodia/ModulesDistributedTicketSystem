package com.ticketmaster.services.persistence.model_repository;

import com.ticketmaster.services.persistence.entity.Ticket;
import com.ticketmaster.services.persistence.entity.User;
import com.ticketmaster.services.persistence.repository.UserRepository;
import com.ticketmaster.services.service.model.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserModelRepository {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    UserModelRepository(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserModel> findAll() {
        List<User> users = userRepository.findAll();

        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<UserModel>>() {
        }.getType();
        return modelMapper.map(users, targetListType);
    }

    public UserModel findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, UserModel.class);
    }

    public UserModel saveUser(UserModel userModel) {
        User user = modelMapper.map(userModel, User.class);

        return modelMapper.map(userRepository.save(user), UserModel.class);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);

        for (Ticket ticket : user.getTickets())
            ticket.getUsers().remove(user);

        userRepository.delete(user);
    }
}

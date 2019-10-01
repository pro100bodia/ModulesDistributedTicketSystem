package com.ticketmaster.services.persistence.repository.jpa;

import com.ticketmaster.services.persistence.entity.Ticket;
import com.ticketmaster.services.persistence.entity.User;
import com.ticketmaster.services.persistence.repository.DataType;
import com.ticketmaster.services.service.model.UserModel;
import com.ticketmaster.services.persistence.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJpaModelRepository implements UserRepository {
    private final UserEntityRepository userEntityRepository;
    private final ModelMapper modelMapper;
    private static final DataType type = DataType.H2;

    UserJpaModelRepository(UserEntityRepository userEntityRepository, ModelMapper modelMapper) {
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataType getType() {
        return type;
    }

    public List<UserModel> findAll() {
        List<User> users = userEntityRepository.findAll();

        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<UserModel>>() {
        }.getType();
        return modelMapper.map(users, targetListType);
    }

    public UserModel findByUsername(String username) {
        User user = userEntityRepository.findByUsername(username);
        return modelMapper.map(user, UserModel.class);
    }

    public UserModel saveUser(UserModel userModel) {
        User user = modelMapper.map(userModel, User.class);

        return modelMapper.map(userEntityRepository.save(user), UserModel.class);
    }

    public void deleteUser(String username) {
        User user = userEntityRepository.findByUsername(username);

        for (Ticket ticket : user.getTickets())
            ticket.getUsers().remove(user);

        userEntityRepository.delete(user);
    }
}

package com.ticketmaster.users.pagination.jpa;

import com.ticketmaster.entity.User;
import com.ticketmaster.model.UserModel;
import com.ticketmaster.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Type;
import java.util.List;

@Primary
public class JpaPaginatedUserModelRepository implements UserRepository {
    private PaginatedUserEntityRepository paginatedUserEntityRepository;
    private ModelMapper modelMapper;
    private int page, size;

    @Autowired
    public JpaPaginatedUserModelRepository(PaginatedUserEntityRepository paginatedUserEntityRepository,
                                           ModelMapper modelMapper) {
        this.paginatedUserEntityRepository = paginatedUserEntityRepository;
        this.modelMapper = modelMapper;
        this.page = 0;
        this.size = 3;
    }

    public JpaPaginatedUserModelRepository(PaginatedUserEntityRepository paginatedUserEntityRepository,
                                           ModelMapper modelMapper, int page, int size) {
        this.paginatedUserEntityRepository = paginatedUserEntityRepository;
        this.modelMapper = modelMapper;
        this.page = page;
        this.size = size;
    }


    @Override
    public Page<UserModel> findAll() {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> users = paginatedUserEntityRepository.findAll(pageable);

        List<User> usersList = users.getContent();

        Type targetPageType = new TypeToken<List<UserModel>>() {
        }.getType();

        List<UserModel> userModelsList = modelMapper.map(usersList, targetPageType);

        return new PageImpl<>(userModelsList);
    }
}

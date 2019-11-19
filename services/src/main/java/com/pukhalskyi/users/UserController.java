package com.pukhalskyi.users;

import com.pukhalskyi.api.dto.UserDto;
import com.pukhalskyi.model.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        Iterable<UserModel> userModels = userService.findAll();
        Type targetIterableType = new TypeToken<Iterable<UserDto>>(){}.getType();
        Iterable<UserDto> userDtos = modelMapper.map(userModels, targetIterableType);

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
}

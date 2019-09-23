package com.ticketmaster.controller;

import com.ticketmaster.api.dto.UserDto;
import com.ticketmaster.service.UserService;
import com.ticketmaster.service.model.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
class UserController {

    private UserService userService;
    private final ModelMapper modelMapper;

    UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    ResponseEntity<List<UserDto>> findAll() {
        List<UserModel> userModels = userService.findAll();

        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<UserDto>>() {
        }.getType();
        List<UserDto> userDtos = modelMapper.map(userModels, targetListType);

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("{username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserModel userModel = userService.getUserByUsername(username);
        UserDto userDto = modelMapper.map(userModel, UserDto.class);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    void saveUser(@RequestBody UserDto userDto) {
        UserModel userModel = modelMapper.map(userDto, UserModel.class);

        userService.saveUser(userModel);
    }

    @PutMapping("{username}")
    void updateUser(@RequestBody UserDto userDto) {
        UserModel userModel = modelMapper.map(userDto, UserModel.class);

        userService.saveUser(userModel);
    }

    @DeleteMapping("{username}")
    void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}

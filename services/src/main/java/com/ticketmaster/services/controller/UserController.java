package com.ticketmaster.services.controller;

import com.ticketmaster.api.dto.UserDto;
import com.ticketmaster.services.persistence.repository.DataType;
import com.ticketmaster.services.service.UserService;
import com.ticketmaster.services.service.model.UserModel;
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
    public ResponseEntity<List<UserDto>> findAll(@RequestHeader(name = "db", required = false, defaultValue = "h2") DataType db) {

        List<UserModel> userModels = userService.findAll(db);

        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<UserDto>>() {
        }.getType();
        List<UserDto> userDtos = modelMapper.map(userModels, targetListType);

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("{username}")
    public ResponseEntity<UserDto> getUserByUsername(
            @RequestHeader(name = "db", required = false, defaultValue = "h2") DataType db,
            @PathVariable String username) {
        UserModel userModel = userService.getUserByUsername(db, username);
        UserDto userDto = modelMapper.map(userModel, UserDto.class);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(
            @RequestHeader(name = "db", required = false, defaultValue = "h2") DataType db,
            @RequestBody UserDto userDto) {
        UserModel userModel = modelMapper.map(userDto, UserModel.class);

        return new ResponseEntity<>(modelMapper.map(userService.saveUser(db, userModel), UserDto.class), HttpStatus.OK);
    }

    @PutMapping("{username}")
    public ResponseEntity<UserDto> updateUser(
            @RequestHeader(name = "db", required = false, defaultValue = "h2") DataType db,
            @RequestBody UserDto userDto) {
        return saveUser(db, userDto);
    }

    @DeleteMapping("{username}")
    public void deleteUser(
            @RequestHeader(name = "db", required = false, defaultValue = "h2") DataType db,
            @PathVariable String username) {
        userService.deleteUser(db, username);
    }
}

package com.ticketmaster.services.controller;

import com.ticketmaster.api.dto.UserDto;
import com.ticketmaster.services.annotation.Timer;
import com.ticketmaster.services.persistence.DataType;
import com.ticketmaster.services.service.UserService;
import com.ticketmaster.services.service.model.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Timer
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(@RequestHeader(name = "db", required = false, defaultValue = "h2") DataType db) {
        List<UserModel> userModels = userService.findAll(db);

        // Define the target type
        java.lang.reflect.Type targetListType = new TypeToken<List<UserDto>>() {
        }.getType();
        List<UserDto> userDtos = modelMapper.map(userModels, targetListType);

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

//    @GetMapping("{username}")
//    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
//        UserModel userModel = userService.getUserByUsername(username);
//        UserDto userDto = modelMapper.map(userModel, UserDto.class);
//
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
//        UserModel userModel = modelMapper.map(userDto, UserModel.class);
//
//        return new ResponseEntity<>(modelMapper.map(userService.saveUser(userModel), UserDto.class), HttpStatus.OK);
//    }
//
//    @PutMapping("{username}")
//    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
//        return saveUser(userDto);
//    }
//
//    @DeleteMapping("{username}")
//    public void deleteUser(@PathVariable String username) {
//        userService.deleteUser(username);
//    }
}

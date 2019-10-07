package com.ticketmaster.controller;

import com.ticketmaster.api.dto.UserDto;
import com.ticketmaster.logging.annotation.LogBefore;
import com.ticketmaster.persistence.repository.DataType;
import com.ticketmaster.service.UserService;
import com.ticketmaster.service.model.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public UserController() {
    }

    UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @LogBefore
    public ResponseEntity<List<UserDto>> findAll(@RequestHeader(name = "db", required = false, defaultValue = "h2") String db) {
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



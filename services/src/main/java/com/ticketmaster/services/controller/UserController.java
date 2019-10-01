package com.ticketmaster.services.controller;

import com.ticketmaster.client.api.dto.UserDto;
import com.ticketmaster.services.persistence.repository.DataType;
import com.ticketmaster.services.service.model.UserModel;
import com.ticketmaster.services.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public StringBuilder getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails ud = (UserDetails) principal;
            StringBuilder sb = new StringBuilder();
            sb.append("username: " + ud.getUsername());
            sb.append("password: " + ud.getPassword());
            sb.append("authorities: ");


            return sb;

        } else {
            return null;
        }
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

//    @GetMapping("{username}")
//    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
//        UserModel userModel = userService.getUserByUsername(username);
//        UserDto userDto = modelMapper.map(userModel, UserDto.class);
//
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }
//
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        UserModel userModel = modelMapper.map(userDto, UserModel.class);

        return new ResponseEntity<>(modelMapper.map(userService.saveUser(userModel), UserDto.class), HttpStatus.OK);
    }
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

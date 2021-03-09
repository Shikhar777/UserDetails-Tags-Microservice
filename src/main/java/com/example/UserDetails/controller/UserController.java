package com.example.UserDetails.controller;

import com.example.UserDetails.dto.UserRequestDto;
import com.example.UserDetails.dto.UserResponseDto;
import com.example.UserDetails.entity.User;
import com.example.UserDetails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    public UserResponseDto saveDetails(@RequestBody UserRequestDto userRequestDto)
    {
        return userService.saveDetails(userRequestDto);
    }

    @GetMapping(value = "/findAll")
    public List<User> findAll()
    {
        return userService.findAll();
    }

    @GetMapping(value = "/findByUserName")
    public UserResponseDto findByUserName(String username)
    {
        return userService.getByUserName(username);
    }
}

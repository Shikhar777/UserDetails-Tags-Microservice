package com.example.UserDetails.controller;

import com.example.UserDetails.dto.UserRequestDto;
import com.example.UserDetails.dto.UserResponseDto;
import com.example.UserDetails.dto.UserUpdateRequestDto;
import com.example.UserDetails.dto.UserUpdateResponseDto;
import com.example.UserDetails.entity.User;
import com.example.UserDetails.service.UserService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
//@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
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

    @GetMapping(value = "/findByUserName/{username}")
    public UserResponseDto findByUserName(@PathVariable("username") String username)
    {
        return userService.getByUserName(username);
    }

    @PutMapping(value = "/updateUser/{username}")
    public UserUpdateResponseDto updateUser(@PathVariable("username") String username, @RequestBody UserUpdateRequestDto userRequestDto)
    {
        return userService.updateUserDetails(username, userRequestDto);
    }

    @GetMapping(value = "/emailaddress")
    public List<String> getEmailAddress(@RequestParam("category") String category) {
        return userService.getEmailAddress(category);
    }
}

package com.example.UserDetails.service;

import com.example.UserDetails.dto.UserRequestDto;
import com.example.UserDetails.dto.UserResponseDto;
import com.example.UserDetails.entity.User;

import java.util.List;

public interface UserService {

    UserResponseDto saveDetails(UserRequestDto userRequestDto);
    List<User> findAll();
    UserResponseDto getByUserName(String username);
}

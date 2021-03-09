package com.example.UserDetails.service.impl;


import com.example.UserDetails.dto.UserRequestDto;
import com.example.UserDetails.dto.UserResponseDto;
import com.example.UserDetails.entity.User;
import com.example.UserDetails.repository.UserRepository;
import com.example.UserDetails.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto saveDetails(UserRequestDto userRequestDto)
    {
        User user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        User savedUser = userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(savedUser, userResponseDto);
        return userResponseDto;
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(list::add);
        return list;
    }

    @Override
    public UserResponseDto getByUserName(String username)
    {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUserName(username));
        if(userOptional.isPresent())
        {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(userOptional.get(), userResponseDto);
            return userResponseDto;
        }
        else
        {
            return null;
        }
    }
}

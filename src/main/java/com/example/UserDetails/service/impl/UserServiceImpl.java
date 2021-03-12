package com.example.UserDetails.service.impl;


import com.example.UserDetails.dto.UserRequestDto;
import com.example.UserDetails.dto.UserResponseDto;
import com.example.UserDetails.dto.UserUpdateRequestDto;
import com.example.UserDetails.dto.UserUpdateResponseDto;
import com.example.UserDetails.entity.User;
import com.example.UserDetails.repository.UserRepository;
import com.example.UserDetails.service.ProducerService;
import com.example.UserDetails.service.UserService;
import org.apache.kafka.common.protocol.types.Field;
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

    @Autowired
    private ProducerService producerService;

    @Override
    public UserResponseDto saveDetails(UserRequestDto userRequestDto)
    {
        User user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        producerService.sendMessageToSearchAfterUpdation(user);
        User savedUser = userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(savedUser, userResponseDto);
        return userResponseDto;
    }

    @Override
    public UserUpdateResponseDto updateUserDetails(String username, UserUpdateRequestDto userUpdateRequestDto)
    {
        User name = new User();
        //BeanUtils.copyProperties(userUpdateRequestDto, name);
        name = userRepository.findByUserName(username);
        name.setCountry(userUpdateRequestDto.getCountry());
        name.setCity(userUpdateRequestDto.getCity());
        name.setState(userUpdateRequestDto.getState());
        name.setBio(userUpdateRequestDto.getBio());
        name.setFirstName(userUpdateRequestDto.getFirstName());
        name.setLastName(userUpdateRequestDto.getLastName());
        name.setProfession(userUpdateRequestDto.getProfession());
        name.setEducation(userUpdateRequestDto.getEducation());
        name.setEmployment(userUpdateRequestDto.getEmployment());
        name.setProfileImage(userUpdateRequestDto.getProfileImage());
        User savedUser = userRepository.save(name);
        UserUpdateResponseDto userResponseDto = new UserUpdateResponseDto();
        BeanUtils.copyProperties(savedUser, userResponseDto);
        producerService.afterUpdation(userResponseDto);
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

package com.example.UserDetails.service.impl;


import com.example.UserDetails.dto.UserRequestDto;
import com.example.UserDetails.dto.UserResponseDto;
import com.example.UserDetails.dto.UserUpdateRequestDto;
import com.example.UserDetails.dto.UserUpdateResponseDto;
import com.example.UserDetails.entity.Education;
import com.example.UserDetails.entity.Employment;
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
    public UserUpdateResponseDto checkStatus(UserUpdateRequestDto userUpdateRequestDto1,UserUpdateRequestDto userUpdateRequestDto)
    {
        String firstName1 = userUpdateRequestDto1.getFirstName();
        String lastName1=userUpdateRequestDto1.getLastName();
        String profession1=userUpdateRequestDto1.getProfession();
        String city1=userUpdateRequestDto1.getCity();
        String country1=userUpdateRequestDto1.getCountry();
        String state1=userUpdateRequestDto1.getState();
        String bio1=userUpdateRequestDto1.getBio();
        String profileImage1=userUpdateRequestDto1.getProfileImage();
        List<Education> education1=userUpdateRequestDto1.getEducation();
        List<Employment> employment1=userUpdateRequestDto1.getEmployment();
        //
        String firstName = userUpdateRequestDto.getFirstName();
        String lastName=userUpdateRequestDto.getLastName();
        String profession=userUpdateRequestDto.getProfession();
        String city=userUpdateRequestDto.getCity();
        String country=userUpdateRequestDto.getCountry();
        String state=userUpdateRequestDto.getState();
        String bio=userUpdateRequestDto.getBio();
        String profileImage=userUpdateRequestDto.getProfileImage();
        List<Education> education=userUpdateRequestDto.getEducation();
        List<Employment> employment=userUpdateRequestDto.getEmployment();
        UserUpdateResponseDto userUpdateResponseDto=new UserUpdateResponseDto();
        //
        if(firstName!="")
        {
            userUpdateResponseDto.setFirstName(firstName);
        }
        else if(firstName1!="") {
            userUpdateResponseDto.setFirstName(firstName1);
        }
        if(lastName!="")
        {
            userUpdateResponseDto.setLastName(lastName);
        }
        else if(lastName1!="") {
            userUpdateResponseDto.setLastName(lastName1);
        }
        if(profession!="")
        {
            userUpdateResponseDto.setProfession(profession);
        }
        else if(profession1!="") {
            userUpdateResponseDto.setProfession(profession1);
        }
        if(city!="")
        {
            userUpdateResponseDto.setCity(city);
        }
        else if(city1!="") {
            userUpdateResponseDto.setCity(city1);
        }
        if(country!="")
        {
            userUpdateResponseDto.setCountry(country);
        }
        else if(country1!="") {
            userUpdateResponseDto.setCountry(country1);
        }
        if(state!="")
        {
            userUpdateResponseDto.setState(state);
        }
        else if(state1!="") {
            userUpdateResponseDto.setState(state1);
        }
        if(bio!="")
        {
            userUpdateResponseDto.setBio(bio);
        }
        else if(bio1!="") {
            userUpdateResponseDto.setBio(bio1);
        }
        if(profileImage!="")
        {
            userUpdateResponseDto.setProfileImage(profileImage);
        }
        else if(profileImage1!="") {
            userUpdateResponseDto.setProfileImage(profileImage1);
        }
        ArrayList<Education> educationsList=new ArrayList<Education>();
        ArrayList<Employment> employmentArrayList=new ArrayList<>();
        if(!education.isEmpty())
        {
            educationsList.addAll(education);
        }
        if(!education1.isEmpty())
        {
            educationsList.addAll(education1);
        }
        if(!employment.isEmpty())
        {
            employmentArrayList.addAll(employment);
        }
        if(!employment1.isEmpty())
        {
            employmentArrayList.addAll(employment1);
        }
        if(!educationsList.isEmpty()){
            userUpdateResponseDto.setEducation(educationsList);
        }
        if(!employmentArrayList.isEmpty())
        {
            userUpdateResponseDto.setEmployment(employmentArrayList);
        }
        return userUpdateResponseDto;
    }

    @Override
    public UserUpdateResponseDto updateUserDetails(String username, UserUpdateRequestDto userUpdateRequestDto)
    {
        try {
            User name;
            name = userRepository.findByUserName(username);
            UserUpdateRequestDto userUpdateRequestDto1 = new UserUpdateRequestDto();
            BeanUtils.copyProperties(name, userUpdateRequestDto1);
            UserUpdateResponseDto userUpdateResponseDto1 = checkStatus(userUpdateRequestDto1, userUpdateRequestDto);
            //
            name.setCountry(userUpdateResponseDto1.getCountry());
            name.setCity(userUpdateResponseDto1.getCity());
            name.setState(userUpdateResponseDto1.getState());
            name.setBio(userUpdateResponseDto1.getBio());
            name.setFirstName(userUpdateResponseDto1.getFirstName());
            name.setLastName(userUpdateResponseDto1.getLastName());
            name.setProfession(userUpdateResponseDto1.getProfession());
            if (!userUpdateResponseDto1.getEducation().isEmpty()) {
                name.setEducation(userUpdateResponseDto1.getEducation());
            }
            if (!userUpdateResponseDto1.getEmployment().isEmpty()) {
                name.setEmployment(userUpdateResponseDto1.getEmployment());
            }
            name.setProfileImage(userUpdateResponseDto1.getProfileImage());
            User savedUser = userRepository.save(name);
            //
            UserUpdateResponseDto userResponseDto = new UserUpdateResponseDto();
            BeanUtils.copyProperties(savedUser, userResponseDto);
            producerService.afterUpdation(userResponseDto);
            return userResponseDto;
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getEmailAddress(String category) {
        List<String> emails = new ArrayList<>();
        try {
            emails = userRepository.findByCategory(category);
            return emails;
        }catch (NullPointerException e )
        {
            e.printStackTrace();
        }
        return emails;
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
        Optional<User> userOptional;
        try {
           userOptional = Optional.ofNullable(userRepository.findByUserName(username));
        } catch (NullPointerException ex) {
            return null;
        }

        if(userOptional.isPresent())
        {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(userOptional.get(), userResponseDto);
            return userResponseDto;
        }
        return null;
    }
}

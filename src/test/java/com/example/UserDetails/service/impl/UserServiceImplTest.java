package com.example.UserDetails.service.impl;
import com.example.UserDetails.dto.*;
import com.example.UserDetails.entity.Badge;
import com.example.UserDetails.entity.Education;
import com.example.UserDetails.entity.Employment;
import com.example.UserDetails.entity.User;
import com.example.UserDetails.repository.BadgeRepository;
import com.example.UserDetails.repository.UserRepository;
import com.example.UserDetails.service.ProducerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void destroy() {

    }

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProducerService producerService;

    @Test
    public void saveDetails() throws IOException
    {
        String[] cat = {"Movies", "Sports", "Travel", "Love", "Disaster"};
        UserRequestDto userResponseDto = new UserRequestDto();
        userResponseDto.setBio("");
        userResponseDto.setEmail("test@gmail.com");
        userResponseDto.setUsername("alpha_tester");
        userResponseDto.setCategory(cat);
        userResponseDto.setProfileImage("");
        userResponseDto.setFirstName("");
        userResponseDto.setLastName("");
        userResponseDto.setEducation(new ArrayList<Education>());
        userResponseDto.setEmployment(new ArrayList<Employment>());
        userResponseDto.setCountry("");
        userResponseDto.setCity("");
        userResponseDto.setState("");
        userResponseDto.setProfession("");
        User user = new User();
        user.setBio("");
        user.setEmail("test@gmail.com");
        user.setUsername("alpha_tester");
        user.setCategory(cat);
        user.setProfileImage("");
        user.setFirstName("");
        user.setLastName("");
        user.setEducation(new ArrayList<Education>());
        user.setEmployment(new ArrayList<Employment>());
        user.setCountry("");
        user.setCity("");
        user.setState("");
        user.setProfession("");
        producerService.sendMessageToSearchAfterUpdation(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        UserResponseDto userResponseDto1= new UserResponseDto();
        userResponseDto1.setBio("");
        userResponseDto1.setEmail("test@gmail.com");
        userResponseDto1.setCategory(cat);
        userResponseDto1.setProfileImage("");
        userResponseDto1.setFirstName("");
        userResponseDto1.setLastName("");
        userResponseDto1.setEducation(new ArrayList<Education>());
        userResponseDto1.setEmployment(new ArrayList<Employment>());
        userResponseDto1.setCountry("");
        userResponseDto1.setCity("");
        userResponseDto1.setState("");
        userResponseDto1.setProfession("");
        assertEquals(userService.saveDetails(userResponseDto),userResponseDto1 );
    }

    @Test
    public void getByUserName() throws IOException
    {
        String userName="alpha_tester";
        User userResponseDto = new User();
        userResponseDto.setBio("");
        userResponseDto.setCity("");
        userResponseDto.setCountry("");
        userResponseDto.setEducation(new ArrayList<Education>());
        userResponseDto.setEmployment(new ArrayList<Employment>());
        String[] cat = {"Movies", "Sports", "Travel", "Love", "Disaster"};
        userResponseDto.setCategory(cat);
        userResponseDto.setState("");
        userResponseDto.setEmail("testuser@gmail.com");
        userResponseDto.setFirstName("");
        LocalDateTime localDateTime = LocalDateTime.now();
        userResponseDto.setJoiningDate(localDateTime);
        userResponseDto.setUsername("alpha_tester");
        userResponseDto.setUserId(1L);
        userResponseDto.setLastName("");
        userResponseDto.setProfession("");
        userResponseDto.setProfileImage("");
        Mockito.when(userRepository.findByUserName(userName)).thenReturn(userResponseDto);
        assertEquals(userService.getByUserName(userName).getFirstName(),userResponseDto.getFirstName());
        Mockito.when(userRepository.findByUserName("")).thenReturn(null);
        assertEquals(userService.getByUserName(""),null);
        Mockito.when(userRepository.findByUserName(userName)).thenThrow(NullPointerException.class);
        assertEquals(userService.getByUserName(userName),null);
    }

    @Test
    public void findAll() throws IOException {
        User user = new User();
        String[] cat = {"Movies", "Sports", "Travel", "Love", "Disaster"};
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setBio("");
        user.setEmail("test@gmail.com");
        user.setUsername("alpha_tester");
        user.setUserId(1L);
        user.setJoiningDate(localDateTime);
        user.setCategory(cat);
        user.setProfileImage("");
        user.setFirstName("");
        user.setLastName("");
        user.setEducation(new ArrayList<Education>());
        user.setEmployment(new ArrayList<Employment>());
        user.setCountry("");
        user.setCity("");
        user.setState("");
        user.setProfession("");
        User user1 = new User();
        String[] cat1 = {"Movies", "Sports", "Travel", "Love", "Disaster"};
        LocalDateTime localDateTime1 = LocalDateTime.now();
        user1.setBio("");
        user1.setEmail("test1@gmail.com");
        user1.setUsername("alpha_tester1");
        user1.setUserId(1L);
        user1.setJoiningDate(localDateTime1);
        user1.setCategory(cat1);
        user1.setProfileImage("");
        user1.setFirstName("");
        user1.setLastName("");
        user1.setEducation(new ArrayList<Education>());
        user1.setEmployment(new ArrayList<Employment>());
        user1.setCountry("");
        user1.setCity("");
        user1.setState("");
        user1.setProfession("");
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        Mockito.when(userRepository.findAll()).thenReturn(list);
        Iterable<User> list1=userRepository.findAll();
        List<User> list2=new ArrayList<>();
        list1.forEach(list2::add);
        assertEquals(list2.size(), 2);
        assertEquals(userService.findAll(),list);
    }

    @Test
    void getEmailAddress() throws IOException
    {
        List<String> email = new ArrayList<>();
        String cat = "Movies";
        email.add("test@gmail.com");
        email.add("test1@gmail.com");
        Mockito.when(userRepository.findByCategory(cat)).thenReturn(email);
        assertEquals(userService.getEmailAddress(cat),email);
        assertEquals(email.size(), 2);
        Mockito.when(userService.getEmailAddress(cat)).thenThrow(NullPointerException.class);
        assertEquals(userService.getEmailAddress(cat),new ArrayList<>());
    }

    @Test
    void updateUserDetails() throws IOException
    {
        String[] cat = {"Movies"};
        String name1="alpha_tester";
        User name2 = new User();
        name2.setBio("Enthusiastic Gamer");
        name2.setProfileImage("abcd");
        name2.setFirstName("Ashish");
        name2.setLastName("Verma");
        name2.setEducation(new ArrayList<Education>());
        name2.setEmployment(new ArrayList<Employment>());
        name2.setCountry("India");
        name2.setCity("Etawah");
        name2.setState("Uttar Pradesh");
        name2.setProfession("Software Developer");
        name2.setEducation(new ArrayList<Education>());
        name2.setEmployment(new ArrayList<Employment>());
        name2.setUsername("alpha_tester");
        name2.setCategory(cat);
        name2.setJoiningDate(LocalDateTime.now());
        name2.setUserId(1L);
        Mockito.when(userRepository.findByUserName(name1)).thenReturn(name2);
        assertEquals(userRepository.findByUserName(name1),name2);
        UserUpdateRequestDto user = new UserUpdateRequestDto();
        UserUpdateRequestDto user1 = new UserUpdateRequestDto();
        //parse(date);
        user.setBio("Enthusiastic Gamer");
        user.setProfileImage("abcd");
        user.setFirstName("Ashish");
        user.setLastName("Verma");
        user.setEducation(new ArrayList<Education>());
        user.setEmployment(new ArrayList<Employment>());
        user.setCountry("India");
        user.setCity("Etawah");
        user.setState("Uttar Pradesh");
        user.setProfession("Software Developer");
        List<Education> educationList = new ArrayList<>();
        Education education = new Education();
        education.setDegree("Secondary");
        education.setGraduationYear(2015L);
        education.setPrimaryDetail("All Subjects");
        education.setEducationId(1L);
        education.setSchool("DAV");
        educationList.add(education);
        //
        List<Employment> employmentList = new ArrayList<>();
        Employment employment = new Employment();
        employment.setEmploymentId(1L);
        employment.setOrganization("Quin Bay");
        employment.setStartYear("2019");
        employment.setEndYear("2021");
        employment.setPosition("Software Developer");
        employmentList.add(employment);
        //parse(date);
        user1.setBio("");
        user1.setProfileImage("");
        user1.setFirstName("");
        user1.setLastName("");
        user1.setEducation(educationList);
        user1.setEmployment(employmentList);
        user1.setCountry("");
        user1.setCity("");
        user1.setState("");
        user1.setProfession("");
        //
        UserUpdateResponseDto userUpdateResponseDto = new UserUpdateResponseDto();
        userUpdateResponseDto.setBio("Enthusiastic Gamer");
        userUpdateResponseDto.setProfileImage("abcd");
        userUpdateResponseDto.setFirstName("Ashish");
        userUpdateResponseDto.setLastName("Verma");
        userUpdateResponseDto.setEducation(new ArrayList<Education>());
        userUpdateResponseDto.setEmployment(new ArrayList<Employment>());
        userUpdateResponseDto.setCountry("India");
        userUpdateResponseDto.setCity("Etawah");
        userUpdateResponseDto.setState("Uttar Pradesh");
        userUpdateResponseDto.setProfession("Software Developer");
        userUpdateResponseDto.setEmployment(employmentList);
        userUpdateResponseDto.setEducation(educationList);
//       userUpdateResponseDto.setUsername("alpha_tester");
//        Mockito.when(userService.checkStatus(user1, user)).thenReturn(userUpdateResponseDto);
        assertEquals(userService.checkStatus(user1,user),userUpdateResponseDto);
        assertEquals(userService.checkStatus(user,user1),userUpdateResponseDto);
        name2.setEmployment(employmentList);
        name2.setEducation(educationList);
        Mockito.when(userRepository.save(name2)).thenReturn(name2);
        userUpdateResponseDto.setUsername(name1);
        assertEquals(userService.updateUserDetails(name1,user),userUpdateResponseDto);

        Mockito.when(userRepository.findByUserName(name1)).thenThrow(NullPointerException.class);
        assertEquals(userService.updateUserDetails(name1,user),null);
    }

}






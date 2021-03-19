package com.example.UserDetails.service.impl;

import com.example.UserDetails.dto.BadgeResponseDto;
import com.example.UserDetails.dto.UserUpdateResponseDto;
import com.example.UserDetails.entity.Badge;
import com.example.UserDetails.entity.Education;
import com.example.UserDetails.entity.Employment;
import com.example.UserDetails.entity.User;
import com.example.UserDetails.repository.BadgeRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BadgeServiceImplTest {

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void destroy() {
    }

    @InjectMocks
    private BadgeServiceImpl badgeService;
    @InjectMocks
    private Producer producer;


    @Mock
    private BadgeRepository badgeRepository;

    @Test
    public void findBadge() throws IOException
    {
        BadgeResponseDto badgeResponseDto = new BadgeResponseDto();
        String name = "alpha_tester";
        //String name = "alpha_tester";
        Long p=badgeService.getResponseFromAnswerClient(name);
        Badge badge=new Badge();
        badge.setRanking("NoBadge");
        badge.setUsername(name);
        badge.setBadgeId(1L);
//        Badge badge1=new Badge();
//        badge1.setRanking("bronze");
//        badge1.setUsername(name);
//        badge1.setBadgeId(2L);
//        Badge badge2=new Badge();
//        badge2.setRanking("silver");
//        badge2.setUsername(name);
//        badge2.setBadgeId(3L);
//
//        Badge badge3=new Badge();
//        badge3.setRanking("gold");
//        badge3.setUsername(name);
//        badge3.setBadgeId(4L);
//
//        Badge badge4=new Badge();
//        badge4.setRanking("platinum");
//        badge4.setUsername(name);
//        badge4.setBadgeId(5L);

        Optional<Badge> badgeResponseDtoOptional=Optional.of(badge);
        Mockito.when(badgeRepository.findByUsername(name)).thenReturn(badgeResponseDtoOptional);
        Mockito.when(badgeRepository.save(badge)).thenReturn(badge);
        badgeResponseDto.setRanking(badge.getRanking());
        badgeResponseDto.setBadgeId(badge.getBadgeId());
        assertEquals(badgeService.findBadge(name),badgeResponseDto);
        Mockito.when(badgeService.findBadge(name)).thenThrow(NullPointerException.class);


//        BadgeResponseDto badgeResponseDto1 = new BadgeResponseDto();
//        badgeResponseDto1.setRanking(badge1.getRanking());
//        badgeResponseDto1.setBadgeId(badge1.getBadgeId());
//        assertEquals(badgeService.findBadge(name),badgeResponseDto1);
//
//        BadgeResponseDto badgeResponseDto2 = new BadgeResponseDto();
//        badgeResponseDto2.setRanking(badge2.getRanking());
//        badgeResponseDto2.setBadgeId(badge2.getBadgeId());
//        assertEquals(badgeService.findBadge(name),badgeResponseDto2);
//
//        BadgeResponseDto badgeResponseDto3 = new BadgeResponseDto();
//        badgeResponseDto3.setRanking(badge3.getRanking());
//        badgeResponseDto3.setBadgeId(badge3.getBadgeId());
//        assertEquals(badgeService.findBadge(name),badgeResponseDto3);
//
//        BadgeResponseDto badgeResponseDto4 = new BadgeResponseDto();
//        badgeResponseDto4.setRanking(badge4.getRanking());
//        badgeResponseDto4.setBadgeId(badge4.getBadgeId());
//        assertEquals(badgeService.findBadge(name),badgeResponseDto4);

    }

    @Test
    public void sendMessageToSearchAfterUpdation()
    {
        User user=new User();
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
        producer.sendMessageToSearchAfterUpdation(user);
        producer.sendMessageToSearchAfterUpdation(null);
    }
    @Test
    public void afterUpdation()
    {
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
        List<Employment> employmentList = new ArrayList<>();
        Employment employment = new Employment();
        employment.setEmploymentId(1L);
        employment.setOrganization("Quin Bay");
        employment.setStartYear("2019");
        employment.setEndYear("2021");
        employment.setPosition("Software Developer");
        employmentList.add(employment);
        List<Education> educationList = new ArrayList<>();
        Education education = new Education();
        education.setDegree("Secondary");
        education.setGraduationYear(2015L);
        education.setPrimaryDetail("All Subjects");
        education.setEducationId(1L);
        education.setSchool("DAV");
        educationList.add(education);
        userUpdateResponseDto.setEmployment(employmentList);
        userUpdateResponseDto.setEducation(educationList);
        producer.afterUpdation(userUpdateResponseDto);
        producer.afterUpdation(null);
    }
}

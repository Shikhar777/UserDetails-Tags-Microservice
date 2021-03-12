package com.example.UserDetails.dto;

import com.example.UserDetails.entity.Education;
import com.example.UserDetails.entity.Employment;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserRequestDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String profession;
    private String city;
    private String country;
    private String state;

    private LocalDateTime joiningDate;
    private String bio;
    private String profileImage;
    private String email;

    private List<Education> education;
    private List<Employment> employment;
    private String[] category;
}

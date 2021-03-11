package com.example.UserDetails.dto;

import com.example.UserDetails.entity.Education;
import com.example.UserDetails.entity.Employment;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String profileCredential;
    private String address;
    private String bio;
    private String profileImage;
    private List<Education> education;
    private List<Employment> employment;
    private String[] category;
}

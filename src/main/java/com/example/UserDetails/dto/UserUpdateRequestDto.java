package com.example.UserDetails.dto;

import com.example.UserDetails.entity.Education;
import com.example.UserDetails.entity.Employment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserUpdateRequestDto {

    private String firstName;
    private String lastName;
    private String profileCredential;
    private String address;
    private String bio;
    private String profileImage;
    private List<Education> education;
    private List<Employment> employment;
}

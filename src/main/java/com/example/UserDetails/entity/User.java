package com.example.UserDetails.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name = "userquora")
@Data
public class User extends BaseEntity{

    @Id
    @GenericGenerator(name = "user_id_seq", strategy = "increment")
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.AUTO)
    private Long userId;
    //@NotBlank
    //@Size(min = 1, max = 100)
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String profileCredential;
    private String address;
    @CreationTimestamp
    @Column
    private LocalDateTime joiningDate;
    private String bio;
    private String profileImage;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Education> education;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Employment> employment;

    @Type(type = "string-array")
    @Column(columnDefinition = "text[]")
    private String[] category;

}

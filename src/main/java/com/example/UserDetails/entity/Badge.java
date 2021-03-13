package com.example.UserDetails.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "badge")
@Data
public class Badge {
    @Id
    @GenericGenerator(name = "user_id_seq", strategy = "increment")
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.AUTO)
    private Long badgeId;

    @NotBlank
    @Column(unique = true)
    private String username;

    private String Ranking;
}

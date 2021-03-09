package com.example.UserDetails.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity(name = "educationquora")
public class Education {

    @Id
    @GenericGenerator(name = "education_id_seq", strategy = "increment")
    @GeneratedValue(generator = "education_id_seq", strategy = GenerationType.AUTO)
    private Long educationId;
    private String school;
    private String primaryDetail;
    private Long graduationYear;
    private String degree;
}

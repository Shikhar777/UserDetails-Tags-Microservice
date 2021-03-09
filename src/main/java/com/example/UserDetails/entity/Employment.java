package com.example.UserDetails.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity(name = "employmentquora")
public class Employment {

    @Id
    @GenericGenerator(name = "employment_id_seq", strategy = "increment")
    @GeneratedValue(generator = "employment_id_seq", strategy = GenerationType.AUTO)
    private Long employmentId;
    private String position;
    private String organization;
    private String startYear;
    private String endYear;
}

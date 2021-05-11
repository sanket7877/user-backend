package com.example.userbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstname ;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private Date dob;

}

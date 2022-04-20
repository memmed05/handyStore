package com.example.handy.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_INFo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

}

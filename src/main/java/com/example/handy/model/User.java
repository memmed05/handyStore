package com.example.handy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private WishList wishList;

}

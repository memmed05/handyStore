package com.example.handy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "WISHLIST")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wihsh_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}

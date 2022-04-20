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

    @OneToOne(mappedBy = "wished")
    private Post post;


}

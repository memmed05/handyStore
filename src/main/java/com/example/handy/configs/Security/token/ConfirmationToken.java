package com.example.handy.configs.Security.token;

import com.example.handy.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    public ConfirmationToken(String token) {
        this.token = token;
    }

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;
}

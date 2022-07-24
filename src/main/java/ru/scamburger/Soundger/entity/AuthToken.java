package ru.scamburger.Soundger.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @OneToOne(mappedBy="authToken", cascade=CascadeType.ALL)
    private User user;

    private String token;

    private Date expiredAt;
}

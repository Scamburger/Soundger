package ru.scamburger.Soundger.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AuthToken {

    @Id
    @Column(name = "user_id")
    private Long user_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String token;

    private Date expiredAt;
}

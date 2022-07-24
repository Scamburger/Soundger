package ru.scamburger.Soundger.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="\"user\"")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AuthToken authToken;

    public User() {
    }

}

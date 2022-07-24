package ru.scamburger.Soundger.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

}

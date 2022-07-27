package ru.scamburger.Soundger.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class AudioTrack {

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne (cascade = CascadeType.ALL)
        private User user;

        private String filePath;

        private int length;

}


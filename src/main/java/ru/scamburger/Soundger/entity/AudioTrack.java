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
        @JoinColumn(name = "uploaded_by_user_id")
        private User user;

        private String name;

        private String filePath;

        private int length;

}


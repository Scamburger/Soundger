package ru.scamburger.Soundger.entity;

import lombok.Data;

import javax.persistence.*;
//fuck
@Entity
@Data
public class AudioTrack {

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne (cascade = CascadeType.MERGE)
        @JoinColumn(name = "UploadedByUserId")
        private User user;

        private String filePath;

        private int length;



}


package ru.scamburger.Soundger.dto;

import lombok.Data;

@Data
public class CurrentUserResponseDto {

    private Long id;

    private String username;

    public CurrentUserResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}

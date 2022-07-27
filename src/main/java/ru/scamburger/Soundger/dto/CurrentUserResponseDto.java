package ru.scamburger.Soundger.dto;

import lombok.Data;
import ru.scamburger.Soundger.entity.User;

@Data
public class CurrentUserResponseDto {

    private Long id;

    private String username;

    public CurrentUserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}

package ru.scamburger.Soundger.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scamburger.Soundger.dto.UserCredentialsRequestDto;
import ru.scamburger.Soundger.service.RegisterService;

@RestController
@RequestMapping("/api")
public class RegisterServiceController {

    private final RegisterService registerService;

    public RegisterServiceController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<String> register(@RequestBody UserCredentialsRequestDto userDto) {
        if (registerService.doRegister(userDto.getUsername(), userDto.getPassword())) {
            return new ResponseEntity<>("User create", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username exist on server", HttpStatus.CONFLICT);
        }
    }

}

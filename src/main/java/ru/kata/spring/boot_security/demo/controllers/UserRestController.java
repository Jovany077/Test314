package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.DTO.UserDTO;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserConverter;
import ru.kata.spring.boot_security.demo.services.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final UserConverter userConverter;

    @Autowired
    public UserRestController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/user/current")
    public ResponseEntity<UserDTO> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        UserDTO userDTO = userConverter.toUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }
}

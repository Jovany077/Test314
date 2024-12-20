package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.DTO.UserDTO;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserConverter;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserConverter userConverter;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService, UserConverter userConverter) {
        this.userService = userService;
        this.roleService = roleService;
        this.userConverter = userConverter;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        User user = userConverter.toUser(userDTO, roleService);
        userService.saveUser(user);
        return  ResponseEntity.ok(userConverter.toUserDTO(user));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userConverter.toUser(userDTO, roleService);
        user.setId(id);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrent(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

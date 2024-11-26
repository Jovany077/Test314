package ru.kata.spring.boot_security.demo.services;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.DTO.UserDTO;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserConverter {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles().stream().map(Role::getId)
                .collect(Collectors.toSet()));
        return userDTO;
    }

    public static User toUser(UserDTO userDTO, RoleService roleService) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        List<Long> roleIds = new ArrayList<>(userDTO.getRoles());
        Set<Role> roles = roleService.findAllById(roleIds);
        user.setRoles(roles);

        return user;
    }
}

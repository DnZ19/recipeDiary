package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.UserCredentialsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.UserDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Role;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.User;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RoleRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.UserRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;
    private final UserService uService;

    public UserController(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder, UserService uService) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
        this.uService = uService;
    }
    @PostMapping("/users")
    public String createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));

        List<Role> userRoles = new ArrayList<>();
        for (String rolename : userDto.roles) {
            Optional<Role> or = roleRepos.findById(rolename);

            userRoles.add(or.get());
        }
        newUser.setRoles(userRoles);

        userRepos.save(newUser);

        return "Done";
    }

    @GetMapping("/roles")
    public List<UserCredentialsDto> getAllUserRoles() {
        return uService.getAllUserCredentials();
    }


}

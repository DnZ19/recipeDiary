package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDiaryDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.UserCredentialsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.UserDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Role;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.User;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RoleRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.UserRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.RecipeDiaryUserService;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;
    private final UserService uService;
    private final RecipeDiaryUserService rduService;

    public UserController(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder, UserService uService, RecipeDiaryUserService rduService) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
        this.uService = uService;
        this.rduService = rduService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUserId(userDto.getUserId());
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(encoder.encode(userDto.getPassword()));

        List<Role> userRoles = new ArrayList<>();
        for (String rolename : userDto.getRoles()) {
            Optional<Role> or = roleRepos.findById(rolename);

            userRoles.add(or.get());
        }
        newUser.setRoles(userRoles);

        User savedUser = userRepos.save(newUser);

        UserDto responseUserDto = new UserDto();
        responseUserDto.setUserId(savedUser.getUserId());
        responseUserDto.setUsername(savedUser.getUsername());
        responseUserDto.setPassword(savedUser.getPassword());
        responseUserDto.setRecipeDiary(savedUser.getRecipeDiary());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUserDto);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> optionalUser = userRepos.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/credentials")
    public List<UserCredentialsDto> getAllUserCredentials() {
        return uService.getAllUserCredentials();
    }

    @GetMapping("/{userId}/recipeDiary")
    public ResponseEntity<UserDto> getDiaryFromUser(@PathVariable Long userId) {
        UserDto userDto = rduService.getDiaryFromUser(userId);

        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDto);
    }

    @PutMapping("{userId}/recipeDiary/{recipeDiaryId}")
    public ResponseEntity<RecipeDiaryDto> linkUserToRecipeDiary(@PathVariable("recipeDiaryId") Long recipeDiaryId, @PathVariable("userId") Long userId){

        RecipeDiaryDto rddto = rduService.linkUserToRecipeDiary(userId, recipeDiaryId);

        return ResponseEntity.ok(rddto);

    }


}

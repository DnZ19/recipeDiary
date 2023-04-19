package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.UserCredentialsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Role;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.User;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository uRepos;


    public UserService(UserRepository uRepos) {
        this.uRepos = uRepos;
    }

    public List<UserCredentialsDto> getAllUserCredentials(){
        List<User> users = new ArrayList<>();
        uRepos.findAll().forEach(users::add);

        return users.stream()
                .map(user -> new UserCredentialsDto(user.getUserId(), user.getUsername(), user.getRoles().stream().map(Role::getRolename).collect(Collectors.toList())))
                .collect(Collectors.toList());

    }


}

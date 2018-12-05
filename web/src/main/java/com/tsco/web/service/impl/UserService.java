package com.tsco.web.service.impl;

import com.tsco.api.domain.UserDTO;
import com.tsco.api.dubboService.UserDubboService;
import com.tsco.web.domain.vo.LoginForm;
import com.tsco.web.domain.vo.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDubboService userDubboService;

    public Optional<UserDTO> creatUser(UserDTO userDTO) {
        return userDubboService.createUser(userDTO);
    }

    public UserDTO login(LoginForm loginForm) {
        UserDTO userDTO = userDubboService.login(generateUserDTO(loginForm));
        return userDTO == null ? null :
                (userDTO.getPassword().equals(loginForm.getPassword()) ? userDTO : null);

    }

    public UserDTO generateUserDTO(RegisterForm registerForm) {
        return UserDTO.builder()
                .email(registerForm.getEmail())
                .password(registerForm.getPassword())
                .build();

    }

    public UserDTO generateUserDTO(LoginForm loginForm) {
        return UserDTO.builder()
                .email(loginForm.getEmail())
                .password(loginForm.getPassword())
                .build();

    }
}

package com.tsco.web.service.impl;

import com.tsco.api.domain.UserDTO;
import com.tsco.api.dubboService.UserDubboService;
import com.tsco.web.domain.vo.LoginForm;
import com.tsco.web.domain.vo.RegisterForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDubboService userDubboService;

    public UserDTO creatUser(UserDTO userDTO) {
        UserDTO user = userDubboService.createUser(userDTO);
        return user.getId() == null ? null : user;
    }

    public UserDTO login(LoginForm loginForm) {
        UserDTO userDTO = userDubboService.findUserByEmail(generateUserDTO(loginForm));
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

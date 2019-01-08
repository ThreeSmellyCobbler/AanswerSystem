package com.tsco.web.service.impl;

import com.tsco.api.domain.UserDTO;
import com.tsco.api.domain.exception.ASException;
import com.tsco.api.dubboService.UserDubboService;
import com.tsco.web.domain.vo.LoginForm;
import com.tsco.web.domain.vo.RegisterForm;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDubboService userDubboService;

    @Autowired
    private RedisTemplate redisTemplate;

    public UserDTO register(RegisterForm registerForm) {
        //校验验证码是否正确
        if (!registerForm.getVerificationCode().equals(redisTemplate.opsForValue().get("RegisterController" + "sendVerificationCode" + registerForm.getEmail()))) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱验证码不正确");
        }
        UserDTO user;
        try {
            user = userDubboService.createUser(generateUserDTO(registerForm));
        } catch (ASException e) {
            throw new WebException(ExceptionCode.INNER_ERROR, "用户保存失败");
        }
        //注册成功后把缓存清掉
        redisTemplate.delete("RegisterController" + "sendVerificationCode" + registerForm.getEmail());
        return user;
    }

    public UserDTO login(LoginForm loginForm) {
        UserDTO userDTO = userDubboService.findUserByEmail(loginForm.getEmail());
        if (userDTO == null) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或密码不正确");
        } else if (!loginForm.getEmail().equals(userDTO.getPassword())) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或密码不正确");
        }
        return userDTO;
    }

    private UserDTO generateUserDTO(RegisterForm registerForm) {
        return UserDTO.builder()
                .email(registerForm.getEmail())
                .password(registerForm.getPassword())
                .build();

    }

}

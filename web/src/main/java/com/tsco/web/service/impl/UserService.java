package com.tsco.web.service.impl;

import com.tsco.api.domain.dto.UserDTO;
import com.tsco.api.domain.exception.ASException;
import com.tsco.api.dubboService.UserDubboService;
import com.tsco.web.domain.vo.LoginForm;
import com.tsco.web.domain.vo.RegisterForm;
import com.tsco.web.domain.vo.UserVo;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import com.tsco.web.service.RedisService;
import com.tsco.web.utils.Constans;
import com.tsco.web.utils.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDubboService userDubboService;

    @Autowired
    private RedisService<String> redisService;

    public UserVo register(RegisterForm registerForm) {
        //校验验证码是否正确
        if (!registerForm.getVerificationCode().equals(redisService.get(RedisKeyUtils.buildKey(Constans.VERIFICATION_KEY_PREFIX, registerForm.getEmail())))) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱验证码不正确");
        }
        UserDTO user = userDubboService.findUserByEmail(registerForm.getEmail());
        UserVo userVo = null;
        if (user != null) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱已经被注册过了");
        }
        try {
            userVo = transferUserDTOToVo(userDubboService.createUser(generateUserDTO(registerForm)));
        } catch (ASException e) {
            throw new WebException(ExceptionCode.INNER_ERROR, "用户保存失败");
        }
        //注册成功后把缓存清掉
        redisService.delete(RedisKeyUtils.buildKey(Constans.VERIFICATION_KEY_PREFIX, registerForm.getEmail()));
        return userVo;
    }

    public UserVo login(LoginForm loginForm) {
        UserDTO userDTO = userDubboService.findUserByEmail(loginForm.getEmail());
        if (userDTO == null) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或密码不正确");
        } else if (!loginForm.getEmail().equals(userDTO.getPassword())) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或密码不正确");
        }
        return transferUserDTOToVo(userDTO);
    }

    private UserDTO generateUserDTO(RegisterForm registerForm) {
        return UserDTO.builder()
                .email(registerForm.getEmail())
                .password(registerForm.getPassword())
                .build();
    }

    private UserVo transferUserDTOToVo(UserDTO userDTO) {
        return UserVo.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .gender(userDTO.getGender())
                .birthday(userDTO.getBirthday())
                .userName(userDTO.getUserName())
                .userRole(userDTO.getUserRole())
                .build();
    }

}

package com.tsco.member.dubboService;

import com.alibaba.dubbo.config.annotation.Service;
import com.tsco.api.domain.dto.UserDTO;
import com.tsco.api.domain.exception.ASException;
import com.tsco.api.dubboService.UserDubboService;
import com.tsco.member.domain.po.User;
import com.tsco.member.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
@Service(version = "1.0.0")
public class UserDubboServiceImpl implements UserDubboService {

    @Autowired
    private UserService userService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dozerBeanMapper.map(userDTO, User.class);
        try {
            userService.saveUser(user);
            log.info("user save success,user is:{}", user);
        } catch (Exception e) {
            log.error("user save fail,user email is:{}", user.getEmail(), e);
            throw new ASException("用户保存失败");
        }
        return userDTO;
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        Optional<User> userOptional = userService.findUserByEmail(email);
        if (!userOptional.isPresent()) {
            log.info("user email is not exist,email is: {}", email);
            return null;
        }
        return dozerBeanMapper.map(userOptional.get(), UserDTO.class);
    }
}

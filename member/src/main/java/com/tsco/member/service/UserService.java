package com.tsco.member.service;

import com.tsco.member.domain.po.User;
import com.tsco.member.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean saveUser(User user) {
        int persit = userMapper.persit(user);
        return persit == 1;
    }

    public Optional<User> findUserByEmail(String email) {
        User user = userMapper.findByEmail(email);
        return Optional.ofNullable(user);
    }
}

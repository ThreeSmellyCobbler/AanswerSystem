package com.tsco.member.service.impl;

import com.tsco.member.domain.po.User;
import com.tsco.member.mapper.user.UserMapper;
import com.tsco.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 2019/01/04 17:21
 *
 * @author <a href="tony_stonner@163.com">fo</a>
 * @record:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void saveUser(User user) {
        userMapper.persit(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        User user = userMapper.findByEmail(email);
        return Optional.ofNullable(user);
    }

}

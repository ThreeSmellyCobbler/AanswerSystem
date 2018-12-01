package com.tsco.member.dubboService;

import com.alibaba.dubbo.config.annotation.Service;
import com.tsco.api.domain.UserDTO;
import com.tsco.api.dubboService.UserDubboService;
import com.tsco.member.domain.po.User;
import com.tsco.member.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service(version = "1.0.0")
public class UserDubboServiceImpl implements UserDubboService {

    @Autowired
    private UserService userService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public void createUser(UserDTO userDTO) {
        User user = dozerBeanMapper.map(userDTO, User.class);
        if (!userService.saveUser(user)) {
            throw new RuntimeException("保存对象失败");
        }
        return;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        Optional<User> userOptional = userService.findUserByEmail(userDTO.getEmail());
        if (userOptional.isPresent()) {
            return dozerBeanMapper.map(userOptional.get(), userDTO.getClass());
        }
        return null;
    }
}

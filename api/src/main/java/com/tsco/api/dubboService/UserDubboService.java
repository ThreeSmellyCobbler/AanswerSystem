package com.tsco.api.dubboService;

import com.tsco.api.domain.UserDTO;

import java.util.Optional;

public interface UserDubboService {

    //创建用户
    Optional<UserDTO> createUser(UserDTO userDTO);

    UserDTO login(UserDTO userDTO);

}

package com.tsco.api.dubboService;

import com.tsco.api.domain.UserDTO;

public interface UserDubboService {

    //创建用户
    UserDTO createUser(UserDTO userDTO);

    UserDTO findUserByEmail(UserDTO userDTO);

}

package com.tsco.api.dubboService;

import com.tsco.api.domain.UserDTO;

public interface UserDubboService {

    //创建用户
    void createUser(UserDTO userDTO);

    UserDTO login(UserDTO userDTO);

}

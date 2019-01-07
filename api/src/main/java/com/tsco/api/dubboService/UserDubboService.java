package com.tsco.api.dubboService;

import com.tsco.api.domain.UserDTO;

public interface UserDubboService {

    /**
     * 创建用户
     * @param userDTO
     * @return 用户实体
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * 通过用户email查找用户
     * @param userDTO
     * @return 用户实体
     */
    UserDTO findUserByEmail(UserDTO userDTO);

}

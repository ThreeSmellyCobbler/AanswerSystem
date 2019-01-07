package com.tsco.member.service;

import com.tsco.member.domain.po.User;

import java.util.Optional;

/**
 * 2019/01/04 17:19
 *
 * @author <a href="tony_stonner@163.com">fo</a>
 * @record:
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param user
     * @return 保存状态
     */
    boolean saveUser(User user);

    /**
     * 通过用户email查找用户
     *
     * @param email
     * @return 用户实体
     */
    Optional<User> findUserByEmail(String email);
}

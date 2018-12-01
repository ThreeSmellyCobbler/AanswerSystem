package com.tsco.member.mapper.user;

import com.tsco.member.domain.po.User;
import com.tsco.member.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据邮箱查找用户
     *
     * @param email 邮箱
     * @return
     */
    User findByEmail(@Param("email") String email);

}


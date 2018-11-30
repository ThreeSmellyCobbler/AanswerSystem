package com.tsco.member.mapper.user;

import com.tsco.member.domain.po.User;
import com.tsco.member.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}


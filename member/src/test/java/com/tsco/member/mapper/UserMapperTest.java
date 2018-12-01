package com.tsco.member.mapper;

import com.tsco.member.domain.po.User;
import com.tsco.member.mapper.user.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextTest() {
        System.out.println(userMapper.load(2L));
    }

    @Test
    public void testInsert() {
        User user = User.builder()
                .userName("1")
                .userRole("1")
                .address("1")
                .email("1")
                .birthday(new Date())
                .password("1")
                .sex("1")
                .build();
        System.out.println(userMapper.persit(user));
    }

    @Test
    public void testUpdate() {
        User user = User.builder()
                .id(7L)
                .userName("2")
                .userRole("2")
                .address("2")
                .email("2")
                .birthday(new Date())
                .password("3")
                .sex("4")
                .build();
        System.out.println(userMapper.update(user));
    }

}

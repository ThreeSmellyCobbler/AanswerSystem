package com.tsco.common.service;

import com.tsco.common.service.impl.MailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailServiceImpl mailService;

    @Test
    public void test() {
    }
}

package com.tsco.web;

import com.tsco.answer.domain.po.Subject;
import com.tsco.answer.mapper.SubjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WebApplicationTest {

    @Autowired
    private SubjectMapper subjectMapper;

    @Test
    public void testLoad() {
        Subject load = subjectMapper.load(1);
    }
}

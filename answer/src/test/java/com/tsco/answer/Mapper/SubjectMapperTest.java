package com.tsco.answer.Mapper;

import com.tsco.answer.domain.po.Subject;
import com.tsco.answer.mapper.SubjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SubjectMapperTest {

    @Autowired
    private SubjectMapper subjectMapper;

    @Test
    public void testLoad() {
        subjectMapper.load(1);
    }

    @Test
    public void testPersit() {
        Subject subject = Subject.builder()
                .answer("A")
                .build();
        subjectMapper.persit(subject);
    }

    @Test
    public void testUpdate() {
        Subject subject = Subject.builder()
                .id(1)
                .answer("C")
                .build();
        subjectMapper.update(subject);
    }

    @Test
    public void testPageQuerySubjects() {
        subjectMapper.pageQuerySubjects(1, 10);
    }


}

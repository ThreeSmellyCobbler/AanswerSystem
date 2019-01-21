package com.tsco.answer.Mapper;

import com.tsco.answer.mapper.AnswerRecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AnswerRecordMapperTest {

    @Autowired
    private AnswerRecordMapper answerRecordMapper;

    @Test
    public void test() {
        answerRecordMapper.load(1);
    }
}

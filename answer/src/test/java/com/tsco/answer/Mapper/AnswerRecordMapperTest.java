package com.tsco.answer.Mapper;

import com.tsco.answer.domain.po.AnswerRecord;
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

    @Test
    public void testPersit() {
        AnswerRecord answerRecord = AnswerRecord.builder()
                .userId(1)
                .subjectId(1)
                .build();
        answerRecordMapper.persit(answerRecord);
    }

    @Test
    public void testUpdate() {
        AnswerRecord answerRecord = AnswerRecord.builder()
                .id(1)
                .userId(2)
                .subjectId(1)
                .build();
        answerRecordMapper.update(answerRecord);
    }
}

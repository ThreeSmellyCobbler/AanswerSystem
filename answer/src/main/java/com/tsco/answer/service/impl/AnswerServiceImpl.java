package com.tsco.answer.service.impl;

import com.tsco.answer.domain.po.AnswerRecord;
import com.tsco.answer.domain.po.Subject;
import com.tsco.answer.mapper.AnswerRecordMapper;
import com.tsco.answer.mapper.SubjectMapper;
import com.tsco.answer.service.AnswerService;
import com.tsco.api.domain.exception.ASException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private AnswerRecordMapper answerRecordMapper;

    @Override
    public List<Subject> getSubjectS(int from, int size) {
        return subjectMapper.pageQuerySubjects(from, size);
    }

    @Override
    public int countSubjects() {
        return subjectMapper.countSubjects();
    }

    @Override
    public void submit(int userId, int subjectId, String result) {
        Subject subject = subjectMapper.load(subjectId);
        if (subject == null) {
            log.error("submit exception userId is:{},subjectId is:{},result is:{}", userId, subjectId, result);
            throw new ASException("题目不存在");
        }
        AnswerRecord answerRecord = AnswerRecord.builder()
                .userId(userId)
                .createdAt(new Date())
                .updatedAt(new Date())
                .result(result)
                .correct(result.equals(subject.getAnswer()))
                .subjectId(subjectId)
                .build();
        answerRecordMapper.persit(answerRecord);
    }
}

package com.tsco.answer.service.impl;

import com.tsco.answer.domain.po.AnswerRecord;
import com.tsco.answer.domain.po.Subject;
import com.tsco.answer.domain.vo.AnswerVo;
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
    public AnswerVo submit(int userId, int subjectId, String result) {
        Subject subject = subjectMapper.load(subjectId);
        if (subject == null) {
            log.error("submit exception userId is:{},subjectId is:{},result is:{}", userId, subjectId, result);
            throw new ASException("题目不存在");
        }
        AnswerRecord answerRecord = AnswerRecord.builder()
                .userId(userId)
                .subjectId(subjectId)
                .result(result)
                .createdAt(new Date())
                .updatedAt(new Date())
                .correct(isCorrectAnswer(subject, result))
                .build();
        answerRecordMapper.persit(answerRecord);
        return AnswerVo.builder()
                .userId(userId)
                .standardAnswer(subject.getAnswer())
                .correct(answerRecord.getCorrect())
                .build();
    }

    @Override
    public Subject getSubjectById(int subjectId) {
        return subjectMapper.load(subjectId);
    }

    private Boolean isCorrectAnswer(Subject subject, String result) {
        switch (subject.getType()) {
            case ALGORITHM:
                //todo 算法题后面处理是否正确
                return true;
            case SINGLE_CHOICE:
                return subject.getAnswer().equals(result);
            case MULTIPLY_CHOICE:
                return subject.getAnswer().equals(result);
        }
        return null;
    }
}

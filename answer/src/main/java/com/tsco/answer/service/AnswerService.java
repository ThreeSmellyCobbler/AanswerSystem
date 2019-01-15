package com.tsco.answer.service;

import com.tsco.answer.domain.po.Subject;

import java.util.List;

public interface AnswerService {


    /**
     * 获取系统题目列表
     *
     * @param from 起始记录
     * @param size 查询总数
     * @return 题目列表
     */
    List<Subject> getSubjectS(int from, int size);

    /**
     * 获取系统题目总数
     *
     * @return 题目总记录
     */
    int countSubjects();

    /**
     * @param userId    用户id
     * @param subjectId 题目id
     * @param result    答案
     * @return 是否正确
     */
    void submit(int userId, int subjectId, String result);
}

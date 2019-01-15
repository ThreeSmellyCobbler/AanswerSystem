package com.tsco.answer.domain.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerRecord {

    private int id;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 题目id
     */
    private int subjectId;

    /**
     * 提交的答案
     */
    private String result;

    /**
     * 答案是否正确
     */
    private Boolean correct;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}

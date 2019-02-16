package com.tsco.web.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitAnswerForm {

    /**
     * 题目id
     */
    Integer subjectId;
    /**
     * 题目类型
     */
    String subjectType;
    /**
     * 答案
     */
    String answer;
}

package com.tsco.answer.domain.po;

import com.tsco.api.domain.enums.SubjectStatusEnum;
import com.tsco.api.domain.enums.SubjectTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 题目
 *
 * @author chen jia
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Subject {

    private int id;

    /**
     * 题目类型
     */
    private SubjectTypeEnum type;
    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目内容(选择题 选项用@分隔)
     */
    private String content;

    /**
     * 题目创建人
     */
    private String createdBy;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 题目状态
     */
    private SubjectStatusEnum status;

    /**
     * 题目创建时间
     */
    private Date createdAt;

    /**
     * 题目更新时间
     */
    private Date updatedAt;
}

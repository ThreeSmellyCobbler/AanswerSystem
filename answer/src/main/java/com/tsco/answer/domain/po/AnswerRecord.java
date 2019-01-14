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

    private int userId;

    private int subjectId;

    private String result;

    private Date createdAt;

    private Date updatedAt;


}

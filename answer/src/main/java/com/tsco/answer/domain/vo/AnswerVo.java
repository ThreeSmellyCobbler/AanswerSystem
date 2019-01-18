package com.tsco.answer.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerVo {

    private int userId;

    private String standardAnswer;

    private boolean correct;

}

package com.tsco.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDTO {

    private int id;

    private String type;

    private String title;

    private String content;

    private Long createdBy;

    private String answer;

    private String status;

    private Date createdAt;

}

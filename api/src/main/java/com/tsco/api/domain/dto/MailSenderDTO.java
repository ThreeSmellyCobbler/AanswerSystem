package com.tsco.api.domain.dto;

import com.tsco.api.domain.enums.EmailTemplateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailSenderDTO implements Serializable {

    private String to;

    private String subject;

    private String content;

    private EmailTemplateEnum templateEnum;

    private Map<String, Object> params;

}

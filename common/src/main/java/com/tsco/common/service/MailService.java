package com.tsco.common.service;

import com.tsco.api.domain.enums.EmailTemplateEnum;

import java.util.Map;

public interface MailService {

    void sendSimpleMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendHtmlMail(String to, String subject, String content);

    void sendHtmlEmailWithTemplate(String to, String subject, EmailTemplateEnum emailTemplate, Map<String, Object> params);

}

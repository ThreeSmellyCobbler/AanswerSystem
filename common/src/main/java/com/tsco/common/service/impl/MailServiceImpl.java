package com.tsco.common.service.impl;


import com.tsco.api.domain.enums.EmailTemplateEnum;
import com.tsco.api.domain.exception.ASException;
import com.tsco.common.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送邮件
     *
     * @param to      收件人
     * @param subject 邮件标题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        log.info("send simple mail begin,to:{},subject:{},content:{}", to, subject, content);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
        log.info("send simple mail finish,to:{},subject:{},content:{}", to, subject, content);
    }

    /**
     * 发送附件的邮件
     *
     * @param to       收件人
     * @param subject  邮件标题
     * @param content  邮件内容
     * @param filePath 附件路径
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(username);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            mimeMessageHelper.addAttachment(fileName, fileSystemResource);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送html格式的邮件
     *
     * @param to      收件人
     * @param subject 邮件标题
     * @param content 邮件内容
     */
    @Override
    @Async
    public void sendHtmlMail(String to, String subject, String content) {
        log.info("send html mail begin,to:{},subject:{},content:{}", to, subject, content);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setFrom(username);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
            log.info("send html mail finish,to:{},subject:{},content:{}", to, subject, content);
        } catch (Exception e) {
            log.error("send html mail fail,to:{},subject:{},content:{}", to, subject, content, e);
            throw new ASException("邮件发送失败");
        }
    }

    /**
     * 使用模板发送邮件
     *
     * @param to            收件人
     * @param subject       邮件标题
     * @param emailTemplate 邮件模板名称
     * @param params        模板参数
     */
    public void sendHtmlEmailWithTemplate(String to, String subject, EmailTemplateEnum emailTemplate, Map<String, Object> params) {
        log.info("send html email with template begin,to:{},subject:{},template:{},params:{}", to, subject, emailTemplate, params);
        String content = renderTemplate(emailTemplate, params);
        if (content.isEmpty()) {
            return;
        }
        sendHtmlMail(to, subject, content);
        log.info("send html email with template finish,to:{},subject:{},template:{},params:{}", to, subject, emailTemplate, params);
    }

    private String renderTemplate(EmailTemplateEnum emailTemplate, Map<String, Object> params) {
        if (params == null) {
            return "";
        }
        Context context = new Context();
        for (String key : params.keySet()) {
            context.setVariable(key, params.get(key));
        }
        return templateEngine.process(emailTemplate.getName(), context);
    }
}

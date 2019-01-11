package com.tsco.web.controller;

import com.tsco.api.domain.dto.MailSenderDTO;
import com.tsco.api.domain.enums.EmailTemplateEnum;
import com.tsco.api.domain.enums.TemplateParam;
import com.tsco.api.domain.exception.ASException;
import com.tsco.api.dubboService.MailSenderDubboService;
import com.tsco.api.utils.RandomUtils;
import com.tsco.api.utils.StringUtils;
import com.tsco.web.domain.Response;
import com.tsco.web.domain.vo.RegisterForm;
import com.tsco.web.domain.vo.UserVo;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import com.tsco.web.service.RedisService;
import com.tsco.web.service.impl.UserService;
import com.tsco.web.utils.Constans;
import com.tsco.web.utils.PatternRegex;
import com.tsco.web.utils.RedisKeyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(description = "注册相关接口", tags = "register")
@RestController
@RequestMapping(path = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSenderDubboService mailSenderDubboService;

    @Autowired
    private RedisService<String> redisService;

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "String"),
            @ApiImplicitParam(name = "verificationCode", value = "验证码", paramType = "String")

    })
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<UserVo> register(@RequestBody RegisterForm registerForm) {
        if (StringUtils.isNullOrEmpty(registerForm.getPassword()) || StringUtils.isNullOrEmpty(registerForm.getVerificationCode())) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "注册参数不能为空");
        }
        checkEmail(registerForm.getEmail());
        UserVo userVo = userService.register(registerForm);
        return Response.SUCCESS(userVo);
    }

    @ApiOperation(value = "获取验证码", notes = "发送到邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "String")
    })
    @GetMapping(value = "/get-verification-code")
    public Response<String> sendVerificationCode(@RequestParam(value = "email") String email) {
        checkEmail(email);
        //在发送邮件之前清理缓存,防止频繁调用接口,导致缓存溢出
        redisService.delete(RedisKeyUtils.buildKey(Constans.VERIFICATION_KEY_PREFIX, email));
        Map<String, Object> map = new HashMap<>();
        String verificationCode = RandomUtils.generateVerificationCode();
        map.put(TemplateParam.VERIFICATION_CODE.getName(), verificationCode);
        MailSenderDTO mailSenderDTO = MailSenderDTO.builder()
                .to(email)
                .subject(EmailTemplateEnum.REGISTER_VERIFICATION.getDesc())
                .templateEnum(EmailTemplateEnum.REGISTER_VERIFICATION)
                .params(map)
                .build();
        try {
            mailSenderDubboService.sendHtmlEmailWithTemplate(mailSenderDTO);
        } catch (ASException as) {
            log.error("mail sender fail", as);
            throw new WebException(ExceptionCode.INNER_ERROR, "邮件服务异常");
        }
        redisService.setWithMinutes(RedisKeyUtils.buildKey(Constans.VERIFICATION_KEY_PREFIX, email), verificationCode, 3);
        return Response.SUCCESS();
    }

    private void checkEmail(String email) {
        if (StringUtils.isNullOrEmpty(email)) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱不能为空");
        }
        //校验email
        if (!email.matches(PatternRegex.email)) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱格式不正确");
        }
    }

}

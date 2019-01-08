package com.tsco.web.controller;

import com.tsco.api.domain.UserDTO;
import com.tsco.api.domain.dto.MailSenderDTO;
import com.tsco.api.domain.enums.EmailTemplateEnum;
import com.tsco.api.domain.enums.TemplateParam;
import com.tsco.api.dubboService.MailSenderDubboService;
import com.tsco.api.utils.RandomUtils;
import com.tsco.api.utils.StringUtils;
import com.tsco.web.domain.Response;
import com.tsco.web.domain.vo.RegisterForm;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import com.tsco.web.service.impl.UserService;
import com.tsco.web.utils.PatternRegex;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(description = "注册相关接口", tags = "register")
@RestController
@RequestMapping(path = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSenderDubboService mailSenderDubboService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "String"),
            @ApiImplicitParam(name = "verificationCode", value = "验证码", paramType = "String")

    })
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response register(@RequestBody RegisterForm registerForm) {
        if (StringUtils.isNullOrEmpty(registerForm.getPassword()) || StringUtils.isNullOrEmpty(registerForm.getVerificationCode())) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "注册参数不能为空");
        }
        checkEmail(registerForm.getEmail());
        UserDTO userDTO = userService.register(registerForm);
        return Response.SUCCESS(userDTO);
    }

    @ApiOperation(value = "获取验证码", notes = "发送到邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "String")
    })
    @RequestMapping(value = "/get-verification-code", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sendVerificationCode(@RequestParam(value = "email") String email) {
        checkEmail(email);
        Map<String, Object> map = new HashMap<>();
        String verificationCode = RandomUtils.generateVerificationCode();
        map.put(TemplateParam.VERIFICATION_CODE.getName(), verificationCode);
        MailSenderDTO mailSenderDTO = MailSenderDTO.builder()
                .to(email)
                .subject(EmailTemplateEnum.REGISTER_VERIFICATION.getDesc())
                .params(map)
                .build();
        mailSenderDubboService.sendHtmlEmailWithTemplate(mailSenderDTO);
        //todo 后面会封装redis
        redisTemplate.delete("RegisterController" + "sendVerificationCode" + email);
        redisTemplate.opsForValue().set("RegisterController" + "sendVerificationCode" + email, verificationCode);
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

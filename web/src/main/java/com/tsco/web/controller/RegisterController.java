package com.tsco.web.controller;

import com.tsco.web.domain.vo.RegisterForm;
import com.tsco.web.domain.Response;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import com.tsco.web.utils.PatternRegex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/register")
public class RegisterController {

    @RequestMapping(method = RequestMethod.POST)
    public Response register(RegisterForm registerForm) {
        if (registerForm.getEmail() == null || registerForm.getPassword() == null) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或者密码不能为空");
        }
        //校验email
        if (registerForm.getEmail().matches(PatternRegex.email)) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱格式不正确");
        }
        return Response.SUCCESS();
    }
}

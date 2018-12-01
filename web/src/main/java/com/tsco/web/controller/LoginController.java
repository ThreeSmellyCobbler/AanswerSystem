package com.tsco.web.controller;

import com.tsco.web.domain.Response;
import com.tsco.web.domain.vo.LoginForm;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginController {

    @RequestMapping(method = RequestMethod.POST)
    public Response login(LoginForm loginForm) {
        if (loginForm.getEmail() == null || loginForm.getPassword() == null) {
            new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或者密码不能为空");
        }
        return Response.SUCCESS();

    }

}

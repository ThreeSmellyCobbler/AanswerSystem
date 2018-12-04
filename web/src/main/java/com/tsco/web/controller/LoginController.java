package com.tsco.web.controller;

import com.tsco.api.domain.UserDTO;
import com.tsco.web.domain.Response;
import com.tsco.web.domain.vo.LoginForm;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import com.tsco.web.service.impl.UserService;
import com.tsco.web.utils.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public Response login(@RequestBody LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        if (loginForm.getEmail() == null || loginForm.getPassword() == null) {
            new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或者密码不能为空");
        }
        UserDTO userDTO = userService.login(loginForm);
        if (userDTO == null) {
            return Response.FAIL(ExceptionCode.INVALID_PARAMETER, "手机号或验证码不正确");
        }
        request.getSession().setAttribute(Constans.USER_ID, userDTO.getId());
        return Response.SUCCESS();

    }

}

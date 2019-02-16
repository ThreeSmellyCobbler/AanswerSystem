package com.tsco.web.controller.user;

import com.tsco.web.domain.Response;
import com.tsco.web.domain.vo.LoginForm;
import com.tsco.web.domain.vo.UserVo;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import com.tsco.web.service.impl.UserService;
import com.tsco.web.utils.Constans;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(description = "登录相关接口", tags = "login")
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "String")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<UserVo> login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        log.info("user login begin,email is: {},password is: {}", loginForm.getEmail(), loginForm.getPassword());
        if (loginForm.getEmail() == null || loginForm.getPassword() == null) {
            new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或者密码不能为空");
        }
        UserVo userVo = userService.login(loginForm);
        request.getSession().setAttribute(Constans.USER_ID, userVo.getId());
        return Response.SUCCESS(userVo);

    }

}

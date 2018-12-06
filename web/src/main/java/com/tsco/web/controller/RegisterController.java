package com.tsco.web.controller;

import com.tsco.api.domain.UserDTO;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "注册相关接口", tags = "register")
@RestController
@RequestMapping(path = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "String")
    })
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response register(@RequestBody RegisterForm registerForm) {
        if (registerForm.getEmail() == null || registerForm.getPassword() == null) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或者密码不能为空");
        }
        //校验email
        if (!registerForm.getEmail().matches(PatternRegex.email)) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱格式不正确");
        }
        UserDTO userDTO = userService.creatUser(userService.generateUserDTO(registerForm));
        if (userDTO != null) {
            return Response.SUCCESS();
        }
        return Response.FAIL(ExceptionCode.INNER_ERROR, "创建用户异常");
    }

}

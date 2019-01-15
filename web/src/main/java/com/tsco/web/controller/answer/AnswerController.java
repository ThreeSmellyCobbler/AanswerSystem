package com.tsco.web.controller.answer;

import com.tsco.answer.service.AnswerService;
import com.tsco.web.controller.BaseController;
import com.tsco.web.domain.Response;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "答题相关接口", tags = "answer")
@RestController
@RequestMapping("/answer")
public class AnswerController extends BaseController {

    @Autowired
    private AnswerService answerService;


    @ApiOperation(value = "提交答案", notes = "submit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectId", value = "题目id", paramType = "Inteager"),
            @ApiImplicitParam(name = "answer", value = "答案", paramType = "String")
    })
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public Response submit(@RequestParam("subjectId") Integer subjectId, @RequestParam("answer") String answer) {
        if (subjectId == null || answer == null) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "参数不能为空");
        }
        answerService.submit(getCurrentUserId(), subjectId, answer);
        return Response.SUCCESS();
    }


}



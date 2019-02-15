package com.tsco.web.controller.answer;

import com.tsco.answer.domain.po.Subject;
import com.tsco.answer.service.AnswerService;
import com.tsco.web.config.annotations.Interceptor;
import com.tsco.web.domain.Response;
import com.tsco.web.domain.vo.PageVo;
import com.tsco.web.domain.vo.SubjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(description = "首页接口", tags = "index")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource(name = "answerServiceImpl")
    private AnswerService answerService;

    @ApiOperation(value = "查询题目列表", notes = "get-subject-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "查询页号,默认第一页1", paramType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小,默认每页10条数据", paramType = "Integer")}
    )
    @RequestMapping(value = "/get-subject-list", method = RequestMethod.GET)
    @Interceptor(needLogin = true)
    public Response<PageVo> getSubjectVo(@RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        log.info("query subject list begin,pageIndex is:{},pageSize is:{}", pageIndex, pageSize);
        int totalSize = answerService.countSubjects();
        //一共多少页
        int totalPage = totalSize / pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        if (pageIndex > totalPage) {
            pageIndex = totalPage;
        }
        List<Subject> subjectS = answerService.getSubjectS((pageIndex - 1) * pageSize, pageSize);
        List<SubjectVo> subjectVos = subjectS.stream().map(this::transferToSubjectVo).collect(Collectors.toList());
        return Response.SUCCESS(PageVo.builder()
                .currentPage(pageIndex)
                .data(subjectVos)
                .totalSize(totalSize)
                .totalPage(totalPage)
                .build());
    }

    private SubjectVo transferToSubjectVo(Subject subject) {
        return SubjectVo.builder()
                .id(subject.getId())
                .title(subject.getTitle())
                .content(Arrays.asList(subject.getContent().split("@")))
                .type(subject.getType().name())
                .build();
    }


}

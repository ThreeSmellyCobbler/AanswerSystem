package com.tsco.web.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    @ApiModelProperty("总记录数")
    private int totalSize;

    @ApiModelProperty("总页数")
    private int totalPage;

    @ApiModelProperty("当前页")
    private int currentPage;

    @ApiModelProperty("题目列表")
    List<SubjectVo> data;

}

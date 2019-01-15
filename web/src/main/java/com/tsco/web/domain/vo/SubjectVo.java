package com.tsco.web.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("题目")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectVo {


    @ApiModelProperty(value = "题目id")
    private int id;

    @ApiModelProperty(value = "题目类型")
    private String type;

    @ApiModelProperty(value = "题目")
    private String title;

    @ApiModelProperty(value = "题目（选项/内容）")
    private List<String> content;
}

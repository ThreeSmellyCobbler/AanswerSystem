package com.tsco.answer.mapper;

import com.tsco.answer.domain.po.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

    /**
     * 分页查询题目
     *
     * @param from 查询起始值(从0开始)
     * @param size 查询记录数
     * @return
     */
    List<Subject> pageQuerySubjects(@Param("from") int from, @Param("size") int size);

    /**
     * 查询题目总数
     *
     * @return
     */
    Integer countSubjects();

}

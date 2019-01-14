package com.tsco.answer.mapper;

import java.util.List;

public interface BaseMapper<T> {

    /**
     * 持久化对象到数据库中
     *
     * @param object
     * @return 影响的行数
     */
    int persit(T object);

    /**
     * 根据id查询
     *
     * @param id
     * @return 查询的对象
     */
    T load(int id);

    /**
     * 根据条件查询
     *
     * @param condition
     * @return 符合条件的记录
     */
    List<T> listByCondition(T condition);

    /**
     * 根据id更新数据
     *
     * @param object
     * @return 影响的行数
     */
    int update(T object);


}

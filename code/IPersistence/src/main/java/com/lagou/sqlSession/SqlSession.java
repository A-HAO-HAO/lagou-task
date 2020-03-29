package com.lagou.sqlSession;

import java.util.List;

public interface SqlSession {

    //查询所有
    public <E> List<E> selectList(String statementid,Object... params) throws Exception;

    //根据条件查询单个
    public <T> T selectOne(String statementid,Object... params) throws Exception;

    //新增记录
    int insert(String statementid,Object o) throws Exception;

    //更新记录
    int update(String statementid,Object o) throws Exception;

    //删除记录
    int delete(String statementid,Object o) throws Exception;

    //为Dao接口生成代理实现类
    public <T> T getMapper(Class<?> mapperClass);


}

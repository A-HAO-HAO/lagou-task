package com.lagou.pojo;

import com.lagou.constant.SqlType;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private DataSource dataSource;

    /*
    *   key: statementid  value:封装好的mappedStatement对象
     * */
    Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }

    public SqlType getSqlTypeByStatementId(String statementId){
        MappedStatement mappedStatement = mappedStatementMap.get(statementId);
        if(mappedStatement == null){
            return null;
        }else{
            return mappedStatement.getSqlType();
        }
    }
}

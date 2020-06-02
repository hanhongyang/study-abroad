package com.example.demo.mapper;

import com.example.demo.model.News;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Select("select * from news")
    @Results(id="newsMap",value={
            @Result(property = "id", column = "id", jdbcType= JdbcType.INTEGER),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "createTime", column = "create_time")
    })
    public List<News> findAllNews();
    //查询前五条新闻
    @Select("select * from news order by create_time desc limit 5")
    @ResultMap("newsMap")
    public List<News> findLastNews();
}

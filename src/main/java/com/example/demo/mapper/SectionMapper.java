package com.example.demo.mapper;

import com.example.demo.model.Section;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SectionMapper {
    //查询所有section
    @Select("select * from section")
    @Results(id="sectionMap",value={
            @Result(property = "id", column = "id", jdbcType= JdbcType.INTEGER),
            @Result(property = "clickCount", column = "click_count"),
            @Result(property = "questionCount", column = "question_count"),
            @Result(property = "schoolId", column = "school_id")
    })
    public List<Section> getAll();

    //查询所有section携带school信息
    @Select("select * from section")
    @Results(id="sectionWithSchoolMap",value={
            @Result(property = "id", column = "id", jdbcType= JdbcType.INTEGER),
            @Result(property = "clickCount", column = "click_count"),
            @Result(property = "questionCount", column = "question_count"),
            @Result(property = "schoolId", column = "school_id"),
            @Result(property = "school", column = "school_id",one = @One(select = "com.example.demo.mapper.SchoolMapper.getById"))
    })
    public List<Section> getAllWithSchool();
}

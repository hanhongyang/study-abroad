package com.example.demo.mapper;

import com.example.demo.model.Country;
import com.example.demo.model.School;

import java.util.List;

import com.example.demo.model.Section;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SchoolMapper {

    //查询所有school
    @Select("select * from school")
    @Results(id="schoolMap",value={
            @Result(property = "schoolId", column = "school_id", jdbcType= JdbcType.INTEGER),
            @Result(property = "name", column = "name"),
            @Result(property = "countryId", column = "country_id"),
            @Result(property = "logo", column = "logo"),
            @Result(property = "city", column = "city"),
            @Result(property = "adminId", column = "admin_id")
    })
    public List<School> getAll();

    //查询一个school
    @Select("select * from school where school_id=#{schoolId}")
    @ResultMap("schoolMap")
    public School getById(@Param("schoolId")int schoolId);
}
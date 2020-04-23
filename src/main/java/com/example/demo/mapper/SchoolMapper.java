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
     List<School> getAll();

    //查询一个school
    @Select("select * from school where school_id=#{schoolId}")
    @ResultMap("schoolMap")
     School getById(@Param("schoolId")int schoolId);
    //查询所有school携带国家
    @Select("select * from school")
    @Results(id="schoolWithCountryMap",value={
            @Result(property = "schoolId", column = "school_id", jdbcType= JdbcType.INTEGER),
            @Result(property = "name", column = "name"),
            @Result(property = "countryId", column = "country_id"),
            @Result(property = "logo", column = "logo"),
            @Result(property = "city", column = "city"),
            @Result(property = "adminId", column = "admin_id"),
            @Result(property = "country", column = "country_id",one = @One(select = "com.example.demo.mapper.CountryMapper.getById"))

    })
     List<School> getAllWithCountry();
    //查询某个国家的所有学校携带国家
    @Select("select * from school where country_id=#{countryId}")
    @ResultMap("schoolWithCountryMap")
     List<School> getAllWithCountryByCountryId(Integer countryId);
    //查询school携带schoolInfo
    @Select("select * from school where school_id=#{schoolId}")
    @Results(id="schoolWithSchoolInfoMap",value={
            @Result(property = "schoolId", column = "school_id", jdbcType= JdbcType.INTEGER),
            @Result(property = "name", column = "name"),
            @Result(property = "countryId", column = "country_id"),
            @Result(property = "schoolInfoId", column = "schoolinfo_id"),
            @Result(property = "logo", column = "logo"),
            @Result(property = "city", column = "city"),
            @Result(property = "adminId", column = "admin_id"),
            @Result(property = "schoolInfo", column = "schoolinfo_id",one = @One(select = "com.example.demo.mapper.SchoolInfoMapper.getById"))

    })
     School getByIdWithSchoolInfo(@Param("schoolId")int schoolId);
}
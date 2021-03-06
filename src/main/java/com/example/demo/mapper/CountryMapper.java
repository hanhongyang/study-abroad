package com.example.demo.mapper;

import com.example.demo.model.Country;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface CountryMapper {
    //查询全部国家信息
    @Select("select * from country")
    @Results(id="countryMap",value={
            @Result(property = "countryId", column = "country_id", jdbcType= JdbcType.INTEGER),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "flag", column = "flag")
    })
    List<Country> getAll();

    //查询一个国家
    @Select("select * from country where country_id=#{countryId}")
    @ResultMap("countryMap")
     Country getById(@Param("countryId")int countryId);
    //插入一条国家信息
    @Insert("Insert into country (country_id,name,code,flag) values(#{countryId},#{name},#{code},#{flag}")
     void insert(@Param("countryId")int countryId,@Param("name")String name,@Param("code")String code,@Param("flag")String flag) ;
    //插入一条国家信息
    @Insert("Insert into country (country_id,name,code,flag) values(#{country.countryId},#{country.name},#{country.code},#{country.flag}")
     void insert2(@Param("country")Country country) ;

    //按学校数量查询国家；倒序；
    @Select("select country.country_id,country.name,country.code,country.flag from country  right join (select country_id,count(*) as count from school group by country_id order by count desc) as b  on country.country_id=b.country_id")
    @ResultMap("countryMap")
     List<Country> popCountries();
}
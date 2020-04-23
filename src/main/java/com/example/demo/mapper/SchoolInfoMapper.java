package com.example.demo.mapper;

import com.example.demo.model.SchoolInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SchoolInfoMapper {

    //schoolId查询某个学校的信息
    @Select("select * from school_info where school_id=#{schoolId}")
    @Results(id="schoolInfoMap",value={
            @Result(property = "id", column = "id", jdbcType= JdbcType.INTEGER),
            @Result(property = "dateOfEstablishment", column = "date_of_establishment"),
            @Result(property = "acceptanceRate", column = "acceptance_rate"),
            @Result(property = "tuition", column = "tuition"),
            @Result(property = "educationalSystem", column = "educational_system"),
            @Result(property = "crimeRate", column = "crimeRate"),
            @Result(property = "averageConsumption", column = "average_consumption"),
            @Result(property = "summary", column = "summary"),
            @Result(property = "address", column = "address"),
            @Result(property = "email", column = "email"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "schoolId", column = "school_id")
    })
     SchoolInfo getBySchoolId(Integer schoolId);
    //Id查询某个学校的信息
    @Select("select * from school_info where id=#{Id}")
    @ResultMap("schoolInfoMap")
      SchoolInfo getById(Integer Id);
}

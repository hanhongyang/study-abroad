package com.example.demo.mapper;

import com.example.demo.model.Major;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface MajorMapper {
    @Select("select * from major where school_id=#{schoolId}")
    @Results(id="MajorMap",value={
            @Result(property = "id", column = "id", jdbcType= JdbcType.INTEGER),
            @Result(property = "college", column = "college"),
            @Result(property = "schoolId", column = "school_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "subject", column = "subject")
    })
    List<Major> getAllBySchoolId(Integer schoolId);

    @Select("select * from major where id=#{id}")
    @ResultMap("MajorMap")
    Major getById(Integer id);
}

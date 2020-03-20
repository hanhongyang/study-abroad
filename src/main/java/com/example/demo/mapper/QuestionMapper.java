package com.example.demo.mapper;

import com.example.demo.model.Question;
import com.example.demo.model.School;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface QuestionMapper {
    //查询某个section里的所有question
    @Select("select * from question where section_id=#{sectionId}")
    @Results(id="questionMap",value={
            @Result(property = "id", column = "id", jdbcType= JdbcType.INTEGER),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "gmt_create", column = "gmt_create"),
            @Result(property = "gmt_modify", column = "gmt_modify"),
            @Result(property = "comment_count", column = "comment_count"),
            @Result(property = "view_count", column = "view_count"),
            @Result(property = "like_count", column = "like_count"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "section_id", column = "section_id"),
            @Result(property = "best_answer", column = "best_answer"),
    })
    public List<Question> getAllBySectionId(@Param("sectionId")Integer sectionId);
}

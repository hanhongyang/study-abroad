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
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModify", column = "gmt_modify"),
            @Result(property = "creator", column = "creator"),
            @Result(property = "commentCount", column = "comment_count"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "sectionId", column = "section_id"),
            @Result(property = "bestAnswer", column = "best_answer"),
    })
    public List<Question> getAllBySectionId(@Param("sectionId")Integer sectionId);

    //添加新问题
    @Insert("insert into question(title,description,gmt_create,gmt_modify,creator,tag,section_id) value(#{title},#{description},#{gmt_create},#{gmt_modify},#{creator},#{tag},#{sectionId})")
    public void addQuestion(@Param("title")String title,
                            @Param("description")String description,
                            @Param("gmt_create")Long gmt_create,
                            @Param("gmt_modify")Long gmt_modify,
                            @Param("creator")Integer creator,
                            @Param("tag")String tag,
                            @Param("sectionId")Integer sectionId);

}

package com.example.demo.mapper;

import com.example.demo.model.Question;
import com.example.demo.model.School;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface QuestionMapper {
    //查询某个section里的所有question携带user
    @Select("select * from question where section_id=#{sectionId}")
    @Results(id="questionWithUserMap",value={
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
            @Result(property = "user", column = "creator",one = @One(select = "com.example.demo.mapper.UserMapper.selectByPrimaryKey"))
    })
    public List<Question> getAllBySectionIdWithUser(@Param("sectionId")Integer sectionId);

    //添加新问题
    @Insert("insert into question(title,description,gmt_create,gmt_modify,creator,tag,section_id) value(#{title},#{description},#{gmt_create},#{gmt_modify},#{creator},#{tag},#{sectionId})")
    public void addQuestion(@Param("title")String title,
                            @Param("description")String description,
                            @Param("gmt_create")Long gmt_create,
                            @Param("gmt_modify")Long gmt_modify,
                            @Param("creator")Integer creator,
                            @Param("tag")String tag,
                            @Param("sectionId")Integer sectionId);
    //查询某个问题携带用户信息
    @Select("select * from question where id=#{id}")
    @ResultMap("questionWithUserMap")
    public Question getByIdWithUser(@Param("id")Integer id);

    //阅读数+1
    @Update("update question set view_count=view_count+1 where id=#{id}")
    void addViewCount(@Param("id")Integer id);

    //查询某个问题
    @Select("select * from question where id=#{id}")
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
            @Result(property = "bestAnswer", column = "best_answer")
    })
    Question getById(Integer id);
    //评论数+1
    @Update("update question set comment_count=comment_count+1 where id=#{id}")
    void addCommentCount(Integer id);
}

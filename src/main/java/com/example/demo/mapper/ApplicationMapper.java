package com.example.demo.mapper;

import com.example.demo.model.Application;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    //保存申请第一步并返回Id
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into application(user_id,school_id,major_id,status,batch,apply_type,step) values(#{userId},#{schoolId},#{majorId},#{stauts},#{batch},#{applyType},#{step})")
    void saveApply1(Application application);

    //更新申请第一步
    @Update("update application set = where id=#{id}")
    void updateApply1(Application application);
    //保存申请第2步
    @Update("update application set name =#{name}," +
                                    "gender=#{gender}," +
                                    "marital_status=#{maritalStatus}," +
                                    "birthday=#{birthday}," +
                                    "passport=#{passport}," +
                                    "email=#{email}," +
                                    "country_id=#{countryId}," +
                                    "phone=#{phone}," +
                                    "recommender=#{recommender}," +
                                    "high_school=#{highSchool}," +
                                    "grade_number=#{gradeNumber}," +
                                    "grade_ranking=#{gradeRanking}," +
                                    "exam_type=#{examType}," +
                                    "exam_time=#{examTime}," +
                                    "total_score=#{totalScore} where id=#{id}")
    void updateApply2(Application application);
    //保存申请第3步
    @Update("")
    void updateApply3(Application application);
    //保存申请第4步
    @Update("")
    void updateApply4(Application application);
    //保存申请第5步
    @Update("")
    void updateApply5(Application application);

    //查询某个用户申请的学校
    @Select("select school_id from application where user_id=#{userId}")
    List<String> schoolListByUserId(Integer userId);
}

package com.example.demo.mapper;

import com.example.demo.model.Application;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ApplicationMapper {

    //保存申请第一步并返回Id
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into application(user_id,school_id,major_id,status,batch,apply_type,step) values(#{userId},#{schoolId},#{majorId},#{stauts},#{batch},#{applyType},#{step})")
    void saveApply1(Application application);

    //更新申请第一步
    @Update("update application set = where id=#{id}")
    void updateApply1();
    //保存申请第2步
    @Update("")
    void updateApply2();
    //保存申请第3步
    @Update("")
    void updateApply3();
    //保存申请第4步
    @Update("")
    void updateApply4();
    //保存申请第5步
    @Update("")
    void updateApply5();

}

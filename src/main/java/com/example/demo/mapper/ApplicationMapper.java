package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ApplicationMapper {

    //保存申请第一步
    @Insert("insert into application() values()")
    void save(Integer schoolId,Integer majorId,Integer stauts,String batch,Integer type,Integer step);

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

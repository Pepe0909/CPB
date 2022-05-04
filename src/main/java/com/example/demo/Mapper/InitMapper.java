package com.example.demo.Mapper;


import com.example.demo.entity.InitEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InitMapper {


    @Select("select * from FT.LIBERTY_LOG")
    @Results(id= "ini", value = {
        @Result(column = "abc", property = "abc"),
        @Result(column = "efg", property = "efg")
    })
    List<InitEntity> selectLog(@Param("abc") String abc);
}

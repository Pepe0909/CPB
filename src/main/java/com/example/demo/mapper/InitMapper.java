package com.example.demo.mapper;


import com.example.demo.entity.InitEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InitMapper {


//    @Select("<script>select * from FT.LIBERTY_LOG " +
//            "<if test= 'abc!=null'>where abc = #{abc} for update</if> " +
//            "</script>")
    @Select("select * from FT.LIBERTY_LOG " +
            "where abc = #{abc}" )
    @Results(id= "ini", value = {
        @Result(column = "abc", property = "abc"),
        @Result(column = "efg", property = "efg")
    })
    List<InitEntity> selectLog(@Param("abc") Integer abc);


    @Update("update FT.LIBERTY_LOG set efg = #{ef}" +
            " where abc = '111' ")
    public int updateLog(@Param("ef") String ef);

    @Update("update FT.LIBERTY_LOG set efg = #{ef}" +
            " where abc = '111' and efg = #{org}")
    public int updateLogWithOptLock(@Param("ef") Integer ef, @Param("org") Integer org);



    @Update("update FT.LIBERTY_LOG set efg = #{ef}, version = version +1" +
            " where abc = '111' and version = #{version}")
    public int updateLogWithVersion(@Param("ef") Integer ef, @Param("version") Integer version);



}

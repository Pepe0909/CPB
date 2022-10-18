package com.example.demo.mapper;


import com.example.demo.entity.AMDBEntity;
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


    @Select("select * from FT.AMDBELND " +
            "where aaa = #{aaa} and bbb = #{bbb}" )
    @Results(id= "dbelnd", value = {
            @Result(column = "appCode", property = "appCode"),
            @Result(column = "aaa", property = "aaa"),
            @Result(column = "bbb", property = "bbb"),
            @Result(column = "status", property = "status")
    })
    List<AMDBEntity> selectAMEntity(@Param("aaa") Integer aaa, @Param("bbb") Integer bbb);

    @Update("update FT.AMDBELND set status = #{trg}" +
            " where aaa = #{aaa} and bbb = #{bbb} and status = #{org}")
    public int updateAMDBWithOptLock(@Param("aaa") Integer aaa
            ,@Param("bbb") Integer bbb, @Param("org") String org, @Param("trg") String trg);


    @Update("update FT.AMDBELND set status = #{status}" +
            " where aaa = #{aaa} and bbb = #{bbb} ")
    public int updateAMDBLog(@Param("aaa") Integer aaa, @Param("bbb") Integer bbb,
                             @Param("status") String status);

}

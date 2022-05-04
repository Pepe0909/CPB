package com.example.demo.dao;

import com.example.demo.Mapper.InitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.InitEntity;

import java.util.List;

@Repository
public class IniDao {


    @Autowired
    InitMapper initMapper;

    public List<InitEntity> selectLog () {
        return initMapper.selectLog("111");
    }





}

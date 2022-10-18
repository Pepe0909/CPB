package com.example.demo.dao;

import com.example.demo.entity.AMDBEntity;
import com.example.demo.mapper.InitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.InitEntity;

import java.util.List;

@Repository
public class IniDao {


    @Autowired
    InitMapper initMapper;

    public List<InitEntity> selectLog (Integer abc) {
        return initMapper.selectLog(abc);
    }

    public int updateLog (String ef) {
        return initMapper.updateLog(ef);
    }

    public boolean updateLogWithOptLock (Integer ef, Integer org) {

        if (initMapper.updateLogWithOptLock(ef, org) > 0) {
            return true;
        }
        return false;

    }

    public boolean updateLogWithVersion (Integer ef, Integer version) {

        if (initMapper.updateLogWithVersion(ef, version) > 0) {
            return true;
        }
        return false;

    }

    public List<AMDBEntity> selectAMDB (Integer aaa, Integer  bbb) {
        return initMapper.selectAMEntity(aaa, bbb);
    }

    public boolean updateAMDBWithOptLock (Integer aaa, Integer bbb, String ori , String trg) {

        if (initMapper.updateAMDBWithOptLock(aaa, bbb, ori, trg) > 0) {
            return true;
        }
        return false;

    }


    public int updateAMDBLog (Integer aaa, Integer bbb, String status) {
        return initMapper.updateAMDBLog(aaa, bbb, status);
    }

}

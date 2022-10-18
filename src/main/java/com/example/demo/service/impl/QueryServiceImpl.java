package com.example.demo.service.impl;

import com.example.demo.dao.IniDao;
import com.example.demo.entity.AMDBEntity;
import com.example.demo.entity.InitEntity;
import com.example.demo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class QueryServiceImpl implements QueryService {


    @Autowired
    IniDao iniDao;

    @Override
    @Transactional(timeout = 10)
    public void query() {


//        TranServiceImpl.lock.isLocked();
//        boolean heldByCurrentThread = TranServiceImpl.lock.tryLock();
//        if(!heldByCurrentThread) {
//            System.out.println("other user is manipulating this trxn.");
//        }

        System.out.println("start to query the particular trxn");

        try {
//            List<InitEntity> list = iniDao.selectLog(111);
//            Integer efg = list.get(0).getEfg();
//            System.out.println(efg);
            int a  = 0;

            List<AMDBEntity> list = iniDao.selectAMDB(111,222);
            String status = list.get(0).getStatus();
            System.out.println(status);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            TranServiceImpl.lock.unlock();
        }
    }

}

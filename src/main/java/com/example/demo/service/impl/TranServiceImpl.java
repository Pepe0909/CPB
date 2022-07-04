package com.example.demo.service.impl;

import com.example.demo.dao.IniDao;
import com.example.demo.entity.InitEntity;
import com.example.demo.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class TranServiceImpl implements TranService {

    public static ReentrantLock lock = new ReentrantLock();

    public static AtomicInteger at = new AtomicInteger(0);

    public static int xxx = 0;

    @Autowired
    IniDao iniDao;

    @Override
    @Transactional
    public void update() {

//        lock.lock();

        try {
            List<InitEntity> list = iniDao.selectLog(111);
            Integer efg = list.get(0).getEfg();
            if (efg == 333) {

                System.out.println("already approved");
                return;
            }


            // TODO： EXECUTE POST
            Thread.sleep(500);
            System.out.println("successfully approved");


            iniDao.updateLog("333");

            int a = 111;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void update2() {

//        lock.lock();

        try {
            List<InitEntity> list = iniDao.selectLog(111);
            Integer efg = list.get(0).getEfg();
            System.out.println("A:" + efg);



            Thread.sleep(8000);
            System.out.println("successfully approved");



            List<InitEntity> list2 = iniDao.selectLog(111);
            System.out.println("A :"+ list2.get(0).getEfg());

//            iniDao.updateLog("333");

            int a = 111;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void update3() {

//        lock.lock();

        try {


            Thread.sleep(1000);
            iniDao.updateLog("444");
            System.out.println("B:successfully approved");


//            iniDao.updateLog("333");

            int a = 111;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
        }
    }

    @Override
    @Transactional
    public void updateWithOptLock() {

        try {
            List<InitEntity> list = iniDao.selectLog(111);
            Integer efg = list.get(0).getEfg();
            if (efg == 444) {
                System.out.println("B:already approved");
                return;
            }
            if (efg == 333) {
                System.out.println("B:other user is doing approval");
                return;
            }

            boolean lockFlag = iniDao.updateLogWithOptLock(333, efg);

            if (lockFlag) {


                iniDao.updateLog("444");
                System.out.println("successfully approved");
                Thread.sleep(50000);

            } else {
                System.out.println("other user is doing approval");
                return;
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
//            System.out.println("E: Exceptions happened, trax roll back");
        } finally {
//            lock.unlock();
        }
    }


    @Override
//    @Transactional
    public void updateWithVersion() {

        try {
            List<InitEntity> list = iniDao.selectLog(111);
            Integer efg = list.get(0).getEfg();
            if (efg == 333) {
                System.out.println("B: already approved");
                return;
            }

            Integer version = list.get(0).getVersion();

            boolean lockFlag = iniDao.updateLogWithVersion(333, version);

            if (lockFlag) {


                Thread.sleep(500);
                // TODO call rest API to do POST
                System.out.println("successfully approved");
            } else {
                System.out.println("already approved");
                return;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
        }
    }


    @Transactional
    public int updateJouranl(List<String> journalList){
        journalList.add("aaa");
        journalList.add("bbb");
        int a = 1 / 0 ;
        journalList.add("ccc");

        return 1;

    }

}

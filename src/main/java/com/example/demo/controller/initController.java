package com.example.demo.controller;



import com.example.demo.dao.IniDao;
import com.example.demo.entity.InitEntity;
import com.example.demo.service.QueryService;
import com.example.demo.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
public class initController {

    @Autowired
    IniDao iniDao;
    @Autowired
    TranService tranService;
    @Autowired
    QueryService queryService;

    Integer c = 20;
    final AtomicInteger a = new AtomicInteger(999);

    final Object lock = new Object();
    String sendDate;

    Boolean transfer= false;

    @RequestMapping("/init")
    public  List<InitEntity>  initMethod () {


        Integer b = 100;


        int abc = 8;

        for (int i = 0; i < 8; i++) {
           new Thread(() -> {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (this) {
                   c++;
                   System.out.println(Thread.currentThread()+"value of c:" + c );
                              }


                int aAfter = a.getAndIncrement();




               System.out.println(Thread.currentThread()+ "---a:"+aAfter);

           }).start();
        }



        List<InitEntity> initEntities = null;
//        initEntities = iniDao.selectLog();


        return initEntities;

    }


    /*
    *
    * wait notify mechanism
    *
    * */
    @RequestMapping("/second")
    public  List<InitEntity>  waitNotify () {



        new Thread(() -> {
            synchronized (lock) {
                while (!transfer) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                sendDate = "send from producer";
                transfer = false;
                lock.notifyAll();

                }


        }).start();

        new Thread(() -> {
            synchronized (lock) {
                while(transfer) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + "received producer's data" + sendDate);
                transfer = true;
                lock.notifyAll();


            }
        }).start();


        List<InitEntity> initEntities = null;
//        initEntities = iniDao.selectLog();


        return initEntities;

    }


    @RequestMapping("/third")
    public String testCompletableFuture() {


        CompletableFuture<String> cpFuture = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "hello world";


        });

        String result = cpFuture.join();





        return result;

    }

    @RequestMapping("/query")
    public String query() {

        queryService.query();





        return "aaa";

    }



    @RequestMapping("/forth")
    public int testTransactin() throws InterruptedException {

//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//                tranService.update();
//
//            }).start();
//        }

//        tranService.update();

//        tranService.update2();

//        new Thread(() -> {
//            tranService.update2();
//        }).start();
//
//        new Thread(() -> {
//            tranService.update();
//        }).start();
//
//        new Thread( ()-> {
//            queryService.query();
//        }).start();


//        for (int i = 0; i < 100; i++) {
//            new Thread(() -> {
//                tranService.updateWithVersion();
//
//            }).start();

//        for (int i = 0; i <50; i++) {
//            new Thread(() -> {
//
//                try {
//                    tranService.updateWithOptLock();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }).start();
//
//        }

//        List<String> journalList = new ArrayList<>();
//        try {
//            tranService.updateJouranl(journalList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(journalList);


        for (int i = 0; i <1; i++) {
            new Thread(() -> {

                try {
                    tranService.updateAMDBWithOptLock();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();

        }


        return 1;

    }

    @Transactional
    public void update() throws InterruptedException {

        iniDao.selectLog(111);

        Thread.sleep(3000);

        iniDao.updateLog("333");
    }




    public static void main(String[] args) {

        List<String> testList = new ArrayList<String>();
        testList.add("a");
        testList.add("b");
        testList.add("c");

        System.out.println(testList.size());

        testList.clear();

        System.out.println(testList.size());


        testList.add("d");

        System.out.println(testList.size());

    }


}

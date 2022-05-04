package com.example.demo.Controller;



import com.example.demo.dao.IniDao;
import com.example.demo.entity.InitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class initController {

    @Autowired
    IniDao iniDao;

    Integer c = 20;
    final AtomicInteger a = new AtomicInteger(999);

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




}

package com.example.demo.service;

import java.util.List;

public interface TranService {

    public void update();

    public void update2();

    public void update3();

    public void updateWithOptLock();

    public void updateWithVersion();

    public int updateJouranl(List<String> journalList);
}

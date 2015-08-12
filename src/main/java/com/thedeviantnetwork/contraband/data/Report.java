package com.thedeviantnetwork.contraband.data;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private static final int CHECKRATE = 10;
    private int currentCheckRate = CHECKRATE;
    private List<Record> records = new ArrayList<Record>();

    public boolean shouldCheck(){
        currentCheckRate--;
        if (currentCheckRate == 0)
        {
            currentCheckRate = CHECKRATE;
            return true;
        }
        return false;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setSolved(){
        for (Record record: records)
            record.setSolved();
    }
}

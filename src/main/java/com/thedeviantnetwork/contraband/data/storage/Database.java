package com.thedeviantnetwork.contraband.data.storage;

import com.google.common.base.Optional;
import com.thedeviantnetwork.contraband.data.ContraBand;
import com.thedeviantnetwork.contraband.data.Material;
import com.thedeviantnetwork.contraband.data.Record;

import java.util.*;

public abstract class Database {

    private List<Record> unsolvedRecords;
    private Set<ContraBand> contraBandCache;

    protected void init(){
        contraBandCache = getContraBandFromStorage();
        unsolvedRecords = getUnSolvedRecordsFromStorage();
    }


    protected abstract void addContraBandToStorage(ContraBand contraBand);

    public void addContraBand(ContraBand contraBand){
        addContraBandToStorage(contraBand);
        contraBandCache = getContraBandFromStorage();
    }

    protected abstract Set<ContraBand> getContraBandFromStorage();

    public Set<ContraBand> getContraBandList() {
        return contraBandCache;
    }

    protected abstract void removeContraBandFromStorage(Material material);

    public void removeContraBand(Material material){
        removeContraBandFromStorage(material);
        contraBandCache = getContraBandFromStorage();
    }


    public void addRecord(Record record){
        for (Record urecord : getUnSolvedRecords())
            if (urecord.equals(record))
                return;
        addRecordToStorage(record);
        unsolvedRecords = getUnSolvedRecordsFromStorage();
    }

    protected abstract void addRecordToStorage(Record record);

    protected abstract List<Record> getUnSolvedRecordsFromStorage();

    public abstract List<Record> getRecords(UUID player);

    public abstract Optional<Record> getRecord(int id);

    public List<Record> getUnSolvedRecords() {
        return unsolvedRecords;
    }
}

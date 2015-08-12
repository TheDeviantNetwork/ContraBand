package com.thedeviantnetwork.contraband.data.storage.YML;

import com.thedeviantnetwork.contraband.data.ContraBand;
import com.thedeviantnetwork.contraband.data.Material;
import com.thedeviantnetwork.contraband.data.Record;
import com.thedeviantnetwork.contraband.data.storage.Database;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class YMLDatabase extends Database {
    @Override
    protected void addContraBandToStorage(ContraBand contraBand) {

    }

    @Override
    protected Set<ContraBand> getContraBandFromStorage() {
        return null;
    }

    @Override
    protected void removeContraBandFromStorage(Material material) {

    }

    @Override
    protected void addRecordToStorage(Record record) {

    }

    @Override
    protected List<Record> getUnSolvedRecordsFromStorage() {
        return null;
    }

    @Override
    public List<Record> getRecords(UUID player) {
        return null;
    }


}

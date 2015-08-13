package com.thedeviantnetwork.contraband.data.storage.SQL;

import com.thedeviantnetwork.contraband.data.Level;
import com.thedeviantnetwork.contraband.data.Material;
import com.thedeviantnetwork.contraband.data.Record;

import java.util.UUID;

public class SQLRecord extends Record {
    private SQLDatabase sqlDatabase;

    public SQLRecord(SQLDatabase sqlDatabase, int id, UUID uuid, Material material, Level status, boolean solved, long timestamp, int x, int y, int z, String worldname) {
        super(id, uuid, material, status, solved, timestamp, x, y, z, worldname);
        this.sqlDatabase = sqlDatabase;
    }

    @Override
    public void setSolved() {
        sqlDatabase.setRecordSolved(id);
        super.setSolved();
    }
}

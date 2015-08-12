package com.thedeviantnetwork.contraband.data.storage.SQL;

import com.thedeviantnetwork.contraband.data.ContraBand;
import com.thedeviantnetwork.contraband.data.Level;
import com.thedeviantnetwork.contraband.data.Material;
import com.thedeviantnetwork.contraband.data.Record;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.UUID;


public class SQLDatabaseTest {

    private static SQLDatabase database;

    @BeforeClass
    public static void testConnection(){
        try {
            database = new SQLDatabase("127.0.0.1", 3306, "contraband", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddContraband(){
        database.addContraBand(new ContraBand("hey", (byte) 0, Level.SUSPICIOUS, 8));
        database.addContraBand(new ContraBand("hey", (byte) 2, Level.SUSPICIOUS, 8));

    }

    @Test
    public void testRemoveContraBand(){
        database.removeContraBand(new Material("hey", (byte) 0));
    }

    @Test
    public void testAddRecord(){
       /* database.addRecord(new Record(UUID.fromString("256f866f-0991-44b6-8a53-9bd3004f03b3"), new Material("hey", (byte) 0), Level.SUSPICIOUS, 0,0,0,"world"));
        for (Record record : database.getUnSolvedRecordsFromStorage())
            record.setSolved();*/

    }

    @Test
    public void getRecordsFromPlayer(){
        System.out.println(database.getRecords(UUID.fromString("256f866f-0991-44b6-8a53-9bd3004f03b3")));
    }

}
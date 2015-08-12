package com.thedeviantnetwork.contraband.data.storage.SQL;

import com.thedeviantnetwork.contraband.data.ContraBand;
import com.thedeviantnetwork.contraband.data.Level;
import com.thedeviantnetwork.contraband.data.Material;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

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
    public void testAdd(){
        database.addContraBand(new ContraBand("hey", (byte) 0, Level.SUSPICIOUS, 8));
        database.addContraBand(new ContraBand("hey", (byte) 2, Level.SUSPICIOUS, 8));

    }

    @Test
    public void testRemove(){
        System.out.println(database.getContraBandFromStorage());
        System.out.println(database.getContraBandList());
        database.removeContraBand(new Material("hey", (byte) 0));
        System.out.println(database.getContraBandFromStorage());
        System.out.println(database.getContraBandList());
    }

}
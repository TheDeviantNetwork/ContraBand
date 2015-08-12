package com.thedeviantnetwork.contraband.data.storage.SQL;

import com.thedeviantnetwork.contraband.data.*;
import com.thedeviantnetwork.contraband.data.storage.Database;

import java.sql.*;
import java.util.*;

public class SQLDatabase extends Database {

    private final String hostname;
    private final int port;
    private final String database;
    private final String user;
    private final String password;
    private Connection connection;


    private static final String[] INIT = {
            "create table if not exists records(\n" +
            "  id int NOT NULL AUTO_INCREMENT unique,\n" +
            "  uuid char(36) not null,\n" +
            "  material char(128) not null,\n" +
            "  level char(128) not null,\n" +
            "  time BIGINT not null,\n" +
            "  solved bool not null,\n" +
            "  x int not null,\n" +
            "  y int not null,\n" +
            "  z int not null,\n" +
            "  world char(64) not null\n" +
            ")",

            "create table if not exists contraband(\n" +
            "   material char(128) not null unique,\n" +
            "   level char(128) not null,\n" +
            "   time int not null\n" +
            ")"
    };

    public SQLDatabase(String hostname, int port, String database, String username, String password) throws SQLException, ClassNotFoundException {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
        checkConnection();
        for (String sql : INIT)
            connection.createStatement().execute(sql);
        init();
    }

    public void checkConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        if (connection!=null && !connection.isClosed())
            return;
        connection = DriverManager.getConnection("jdbc:mysql://"
                        + this.hostname + ":" + this.port + "/" + this.database,
                this.user, this.password);
    }


    private Record getRecordFromResultSet(ResultSet resultSet) throws SQLException {
        return new SQLRecord(
                this,
                resultSet.getInt("id"),
                UUID.fromString(resultSet.getString("UUID")),
                new Material(resultSet.getString("material")),
                Level.valueOf(resultSet.getString("level")),
                resultSet.getBoolean("solved"),
                resultSet.getLong("time"),
                resultSet.getInt("x"),
                resultSet.getInt("y"),
                resultSet.getInt("z"),
                resultSet.getString("world")
        );
    }


    private final static String GET_REPORT = "SELECT * FROM records WHERE uuid=?";
    @Override
    public List<Record> getRecords(UUID player) {
        List<Record> recordList = new ArrayList<Record>();
        try {
            checkConnection();
            PreparedStatement statement = connection.prepareStatement(GET_REPORT);
            statement.setString(1, player.toString());
            ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()){
                recordList.add(getRecordFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
        return recordList;
    }

    private static final String ADD_RECORD = "INSERT INTO records " +
            "(uuid, material, level, time, solved, x, y, z, world)" +
            " values (?,?,?,?,?,?,?,?,?)";
    @Override
    protected void addRecordToStorage(Record record) {
        try {
            checkConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_RECORD);
            statement.setString (1 , record.getUuid().toString());
            statement.setString (2 , record.getMaterial().toString());
            statement.setString (3 , record.getLevel().name());
            statement.setLong(4, record.getTimestamp());
            statement.setBoolean(5, record.isSolved());
            statement.setInt(6, record.getX());
            statement.setInt    (7 , record.getY());
            statement.setInt    (8 , record.getZ());
            statement.setString(9, record.getWorldname());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final String UPDATE_RECORD = "UPDATE records SET solved=1 WHERE id=?";
    void setRecordSolved(int id){
        try {
            checkConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_RECORD);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static final String GET_UNSOLVED_REPORTS = "SELECT * FROM records WHERE solved=0";
    @Override
    protected List<Record> getUnSolvedRecordsFromStorage() {
        List<Record> records = new ArrayList<Record>();
        try {
            checkConnection();
            ResultSet resultSet = connection.prepareStatement(GET_UNSOLVED_REPORTS).executeQuery();
            while (resultSet.next()){
                records.add(getRecordFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return records;
    }


    private final static String ADD_CONTRABAND = "insert into contraband values(?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE `level`=?, `time`=?";
    @Override
    protected void addContraBandToStorage(ContraBand contraBand) {
        try {
            checkConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_CONTRABAND);
            statement.setString(1, contraBand.toString());
            statement.setString(2, contraBand.getLevel().name());
            statement.setInt(3, contraBand.getTime());
            statement.setString(4, contraBand.getLevel().name());
            statement.setInt(5, contraBand.getTime());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final static String GET_CONTRABAND = "select * from contraband";
    @Override
    protected Set<ContraBand> getContraBandFromStorage() {
        Set<ContraBand> contraBandList = new HashSet<ContraBand>();
        try {
            checkConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(GET_CONTRABAND);
            while (resultSet.next())
                contraBandList.add(new ContraBand(
                        resultSet.getString("material"),
                        Level.valueOf(resultSet.getString("level")),
                        resultSet.getInt("time")));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contraBandList;
    }

    private final static String REMOVE_CONTRABAND = "DELETE FROM contraband WHERE material=?";
    @Override
    protected void removeContraBandFromStorage(Material material) {
        try {
            checkConnection();
            PreparedStatement statement = connection.prepareStatement(REMOVE_CONTRABAND);
            statement.setString(1, material.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

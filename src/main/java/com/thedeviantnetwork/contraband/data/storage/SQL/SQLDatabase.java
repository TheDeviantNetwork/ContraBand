package com.thedeviantnetwork.contraband.data.storage.SQL;

import com.google.common.base.Optional;
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
            "  uuid char(36) not null,\n" +
            "  material char(128) not null,\n" +
            "  time BIGINT not null,\n" +
            "  solved bool not null\n" +
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


    private final static String GET_REPORT = "";
    @Override
    protected Optional<Report> getReportFromStorage(UUID player) {
        try {
            PreparedStatement statement = connection.prepareStatement(GET_REPORT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.absent();
    }

    @Override
    protected Map<UUID, Report> getUnSolvedReportsFromStorage() {
        Map<UUID, Report> reports = new HashMap<UUID, Report>();
        return reports;
    }

    @Override
    public void addRecord(Record report) {

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

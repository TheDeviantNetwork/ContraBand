package com.thedeviantnetwork.contraband;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public Config(FileConfiguration fileConfiguration){
        this.host =   fileConfiguration.getString("database.host");
        this.port       =   fileConfiguration.getInt("database.port");
        this.database   =   fileConfiguration.getString("database.database");
        this.username =   fileConfiguration.getString("database.username");
        this.password   =   fileConfiguration.getString("database.password");
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Config{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", database='" + database + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

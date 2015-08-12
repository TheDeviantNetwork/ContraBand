package com.thedeviantnetwork.contraband;

import com.thedeviantnetwork.contraband.commands.CommandCB;
import com.thedeviantnetwork.contraband.data.storage.Database;
import com.thedeviantnetwork.contraband.data.storage.SQL.SQLDatabase;
import com.thedeviantnetwork.contraband.listeners.ContraBandListener;
import com.thedeviantnetwork.contraband.scanner.ContraBandScanner;
import nl.lolmewn.stats.api.StatsAPI;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class ContraBandPlugin extends JavaPlugin {
    private StatsAPI statsAPI;
    private Database database;
    private Config config;

    @Override
    public void onEnable() {
        setupConfig();
        setupDatabase();
        registerCommands();
        registerScanner();
    }

    private void setupConfig(){
        saveDefaultConfig();
        this.config = new Config(getConfig());
        getLogger().info(config.toString());
    }

    private void setupDatabase(){
        try {
            this.database = new SQLDatabase(
                    config.getHost(),
                    config.getPort(),
                    config.getDatabase(),
                    config.getUsername(),
                    config.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void registerScanner(){
        ContraBandScanner scanner = new ContraBandScanner(database);
        getServer().getPluginManager().registerEvents(new ContraBandListener(scanner), this);
    }

    private void registerCommands(){
        getCommand("cb").setExecutor(new CommandCB(database));
    }


    private boolean setupStatsAPI(){
        RegisteredServiceProvider<StatsAPI> stats = getServer().getServicesManager().getRegistration(nl.lolmewn.stats.api.StatsAPI.class);
        if (stats!= null) {
            statsAPI = stats.getProvider();
        }
        return (statsAPI != null);
    }


}

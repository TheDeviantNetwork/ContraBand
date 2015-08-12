package com.thedeviantnetwork.contraband;

import nl.lolmewn.stats.api.StatsAPI;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class ContraBandPlugin extends JavaPlugin {
    private StatsAPI statsAPI;

    @Override
    public void onEnable() {
        setupStatsAPI();
    }



    private boolean setupStatsAPI(){
        RegisteredServiceProvider<StatsAPI> stats = getServer().getServicesManager().getRegistration(nl.lolmewn.stats.api.StatsAPI.class);
        if (stats!= null) {
            statsAPI = stats.getProvider();
        }
        return (statsAPI != null);
    }


}

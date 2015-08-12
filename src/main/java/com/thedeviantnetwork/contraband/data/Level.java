package com.thedeviantnetwork.contraband.data;

import org.bukkit.ChatColor;

public enum Level {
    SUSPICIOUS(ChatColor.YELLOW),
    WARNING(ChatColor.RED),
    BAN(ChatColor.DARK_RED);

    ChatColor color;
    Level(ChatColor color){
        this.color = color;
    }

    public ChatColor getColor() {
        return color;
    }
}

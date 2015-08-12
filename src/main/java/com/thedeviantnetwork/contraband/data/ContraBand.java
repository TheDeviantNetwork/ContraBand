package com.thedeviantnetwork.contraband.data;

public class ContraBand extends Material {

    private Level level;
    private int time;

    public ContraBand(String id, Byte subid, Level level, int time){
        super(id, subid);
        this.level = level;
        this.time = time;
    }

    public ContraBand(String id, Byte subid, Level level) {
        this(id, subid, level, 0);
    }

    public ContraBand(String idmixed, Level level, int time){
        super(idmixed);
        this.level = level;
        this.time = time;
    }

    public Level getLevel() {
        return level;
    }

    public int getTime() {
        return time;
    }
}

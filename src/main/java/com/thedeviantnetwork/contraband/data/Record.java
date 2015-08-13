package com.thedeviantnetwork.contraband.data;

import org.bukkit.Bukkit;

import java.util.Date;
import java.util.UUID;

public class Record {

    protected int id;
    private UUID uuid;
    private Material material;
    private Level status;
    private boolean solved;
    private long timestamp;
    private int x;
    private final int y;
    private final int z;
    private final String worldname;

    public Record(UUID uuid, ContraBand contraBand, int x, int y, int z, String worldname) {
        this(0, uuid, contraBand, contraBand.getLevel(), false , System.currentTimeMillis(),x,y,z,worldname);
    }

    public Record(int id, UUID uuid, Material material, Level status, boolean solved, long timestamp, int x, int y, int z, String worldname) {
        this.id = id;
        this.uuid = uuid;
        this.material = material;
        this.status = status;
        this.solved = solved;
        this.timestamp = timestamp;
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldname = worldname;
    }

    public Level getLevel() {
        return status;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved() {
        this.solved = true;
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getWorldname() {
        return worldname;
    }

    public String getMessage(){
        return getLevel().getColor() + "id: " + id + " player: " + Bukkit.getOfflinePlayer(getUuid()).getName() + " Level: " + getLevel() + "Mat: " + getMaterial() + " at: " + new Date(timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Record){
            Record ob = (Record) obj;
            return ob.getMaterial().equals(material) && ob.getUuid().equals(uuid) && !isSolved();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Record{" +
                "uuid=" + uuid +
                ", material=" + material +
                ", status=" + status +
                ", solved=" + solved +
                ", timestamp=" + timestamp +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", worldname='" + worldname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}

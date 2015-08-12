package com.thedeviantnetwork.contraband.data;

import java.util.UUID;

public class Record {

    private UUID uuid;
    private Material material;
    private Level status;
    private boolean solved;
    private long timestamp;

    public Record(UUID uuid, Material message, Level status) {
        this(uuid, message, status, false , System.currentTimeMillis());
    }

    public Record(UUID uuid, Material material, Level status, boolean solved, long timestamp) {
        this.uuid = uuid;
        this.material = material;
        this.status = status;
        this.solved = solved;
        this.timestamp = timestamp;
    }

    public Level getStatus() {
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
}

package com.thedeviantnetwork.contraband.data;

import java.util.Objects;

public class Material {

    private String id;
    private Byte data;

    public Material(String id, Byte subid) {
        this.id = id;
        this.data = subid;
    }

    public Material(String mixed){
        String[] d = mixed.split(":");
        this.id = d[0];
        this.data = Byte.decode(d[1]);
    }

    public String getId() {
        return id;
    }

    public Byte getData() {
        return data;
    }

    @Override
    public String toString() {
        return id + ":" + data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Material)
            return id.equals(((Material)obj).id) && data.equals(((Material) obj).data);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,data);
    }
}

package com.thedeviantnetwork.contraband.data.storage;

import com.google.common.base.Optional;
import com.thedeviantnetwork.contraband.data.ContraBand;
import com.thedeviantnetwork.contraband.data.Material;
import com.thedeviantnetwork.contraband.data.Record;
import com.thedeviantnetwork.contraband.data.Report;

import java.util.*;

public abstract class Database {

    private Map<UUID, Report> reportCache;
    private Set<ContraBand> contraBandCache;

    protected void init(){
        contraBandCache = getContraBandFromStorage();
        reportCache = getUnSolvedReportsFromStorage();
    }

    protected abstract Map<UUID, Report> getUnSolvedReportsFromStorage();

    protected abstract Optional<Report> getReportFromStorage(UUID player);

    public Report getReport(UUID player){
        if (reportCache.containsKey(player))
            return reportCache.get(player);

        Optional<Report> report = getReportFromStorage(player);
        if (!report.isPresent())
            report = Optional.of(new Report());
        reportCache.put(player, report.get());
        return report.get();
    }

    protected Map<UUID, Report> getReportCache() {
        return reportCache;
    }

    protected abstract void addContraBandToStorage(ContraBand contraBand);

    public void addContraBand(ContraBand contraBand){
        addContraBandToStorage(contraBand);
        contraBandCache = getContraBandFromStorage();

    }

    protected abstract Set<ContraBand> getContraBandFromStorage();

    public Set<ContraBand> getContraBandList() {
        return contraBandCache;
    }

    protected abstract void removeContraBandFromStorage(Material material);

    public void removeContraBand(Material material){
        removeContraBandFromStorage(material);
        contraBandCache = getContraBandFromStorage();
    }

    public abstract void addRecord(Record report);

}

package com.tanmay192.CoronaVirusTracker.services;

import com.tanmay192.CoronaVirusTracker.models.LocationStats;

import java.util.List;

public interface CoronaVirusDataAccessService {
    public List<LocationStats> getAllLocationStats();

    public Long getTotalConfirmedCases();

    public Long getChangeInConfirmedCases();
}

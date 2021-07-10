package com.tanmay192.CoronaVirusTracker.services;

import com.tanmay192.CoronaVirusTracker.models.LocationStats;
import com.tanmay192.CoronaVirusTracker.repository.CoronaVirusDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoronaVirusDataAccessServiceImpl implements CoronaVirusDataAccessService{
    @Autowired
    private CoronaVirusDataRepository coronaVirusDataRepository;

    @Override
    public List<LocationStats> getAllLocationStats() {
        return coronaVirusDataRepository.findAll();
    }

    @Override
    public Long getTotalConfirmedCases() {
        Long totalConfirmedCases = 0L;
        for (Long totalCases: coronaVirusDataRepository.findAllByNewNumberOfCases()) {
            totalConfirmedCases += totalCases;
        }
        return totalConfirmedCases;
    }

    @Override
    public Long getChangeInConfirmedCases() {
        Long changeInConfirmedCases = 0L;
        for (Long changeInNumberOfCases: coronaVirusDataRepository.findAllByChangeInNumberOfCases()) {
            changeInConfirmedCases+= changeInNumberOfCases;
        }
        return changeInConfirmedCases;
    }
}

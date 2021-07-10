package com.tanmay192.CoronaVirusTracker.repository;

import com.tanmay192.CoronaVirusTracker.models.LocationStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoronaVirusDataRepository extends JpaRepository<LocationStats, Long> {
    @Query(value = "SELECT NEW_NUMBER_OF_CASES FROM LOCATION_STATS",
            nativeQuery = true)
    public List<Long> findAllByNewNumberOfCases();

    @Query(value = "SELECT CHANGE_IN_NUMBER_OF_CASES FROM LOCATION_STATS",
            nativeQuery = true)
    public List<Long> findAllByChangeInNumberOfCases();
}

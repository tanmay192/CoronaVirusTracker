package com.tanmay192.CoronaVirusTracker.repository;

import com.tanmay192.CoronaVirusTracker.models.LocationStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoronaVirusDataRepository extends JpaRepository<LocationStats, Long> {
}

package com.servinguno.arduinoserver;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, OffsetDateTime> {

    @Query("SELECT t FROM Temperature t ORDER BY t.id DESC")
    List<Temperature> findLatestTemperatures(Pageable pageable);
}
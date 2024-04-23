package com.servinguno.arduinoserver;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;

public interface TemperatureRepository extends JpaRepository<Temperature, OffsetDateTime> {
}
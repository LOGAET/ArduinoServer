package com.servinguno.arduinoserver;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@Setter
@Entity
@Table(name = "temperature")
public class Temperature {
    @Id
    @Column(name = "datatime_id", nullable = false)

    private OffsetDateTime id;

    @Column(name = "temperature", nullable = false, precision = 5, scale = 2)
    private BigDecimal temperature;

    public Temperature(String data, OffsetDateTime offsetDateTime) {
        this.temperature = new BigDecimal(data);
        this.id = offsetDateTime;

    }

    public Temperature() {

    }
}
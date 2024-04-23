package com.servinguno.arduinoserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final TemperatureRepository temperatureRepository;

    @PostMapping("/message")
    public void getDataFromArduino(@RequestBody String data) {
        System.out.println("Temp: " + data);
        temperatureRepository.save(new Temperature(data, ZonedDateTime.now(
                ZoneId.of("Europe/Moscow")).toOffsetDateTime()));
    }
}

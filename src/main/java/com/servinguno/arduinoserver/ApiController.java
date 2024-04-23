package com.servinguno.arduinoserver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final TemperatureRepository temperatureRepository;

    @PostMapping("/message")
    public OffsetDateTime getDataFromArduino(@RequestBody String data) {
        System.out.println("Temp: " + data);
        var date = ZonedDateTime.now(
                ZoneId.of("Europe/Moscow")
        ).toOffsetDateTime().plusHours(3);
        temperatureRepository.save(new Temperature(data, date));
        return date.minusHours(3);
    }
}

package com.servinguno.arduinoserver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final TemperatureRepository temperatureRepository;

    @PostMapping("/message")
    public OffsetDateTime getDataFromArduino(@RequestBody String data) {
        data = extractNumbersAndDots(data);
        System.out.println("Temp: " + data);
        var date = ZonedDateTime.now(
                ZoneId.of("Europe/Moscow")
        ).toOffsetDateTime().plusHours(3);
        temperatureRepository.save(new Temperature(data, date));
        return date.minusHours(3);
    }

    @GetMapping("/data")
    public java.util.List<Temperature> getDataFromDB(){
        return temperatureRepository.findAll();
    }

    public static String extractNumbersAndDots(String input) {
        Pattern pattern = Pattern.compile("[0-9.]+");
        Matcher matcher = pattern.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group());
        }

        return sb.toString();
    }

}

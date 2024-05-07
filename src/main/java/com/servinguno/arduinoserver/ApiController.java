package com.servinguno.arduinoserver;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

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
        ).toOffsetDateTime();
        temperatureRepository.save(new Temperature(data, date));
        return date;
    }

    @GetMapping("/data")
    public java.util.List<Temperature> getDataFromDB(@RequestParam Integer limit){
        return temperatureRepository.findLatestTemperatures(PageRequest.of(0, limit));
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

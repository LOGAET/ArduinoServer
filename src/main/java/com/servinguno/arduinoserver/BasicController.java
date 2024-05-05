package com.servinguno.arduinoserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class BasicController {
    private final TemperatureRepository temperatureRepository;
    @GetMapping
    public String getDataFromArduino(@ModelAttribute String data, Model model){
        java.util.List<Temperature> repositoryAlldata = temperatureRepository.findAll();
        model.addAttribute("repositoryAlldata", repositoryAlldata);
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss"));
        return "./index/index.html";
    }
}

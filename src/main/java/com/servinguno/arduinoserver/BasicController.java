package com.servinguno.arduinoserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class BasicController {
    private final TemperatureRepository temperatureRepository;
    @GetMapping
    public String getDataFromArduino(@ModelAttribute String data, Model model){
        java.util.List<Temperature> repositoryAlldata = temperatureRepository.findAll();
        model.addAttribute("repositoryAlldata", repositoryAlldata);
        return "./index/index.html";
    }
}

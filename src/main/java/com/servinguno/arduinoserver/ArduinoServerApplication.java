package com.servinguno.arduinoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ArduinoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArduinoServerApplication.class, args);
    }

}


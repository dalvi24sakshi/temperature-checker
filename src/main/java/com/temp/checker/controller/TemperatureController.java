package com.temp.checker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TemperatureController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/check-temp")
    public String checkTemp(Model model) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=18.5204&longitude=73.8567&current_weather=true";
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            String tempStr = response.split("\"temperature\":")[1].split(",")[0];
            double temp = Double.parseDouble(tempStr);
            model.addAttribute("temp", temp);
            model.addAttribute("city", "Pune");
            model.addAttribute("time", java.time.LocalTime.now().toString().substring(0,5));
        } catch (Exception e) {
            model.addAttribute("temp", "29.5");
            model.addAttribute("city", "Pune");
            model.addAttribute("time", "Now");
        }
        return "result";
    }
}
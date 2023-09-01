package com.example.demo.controller;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.DarkModeDTO;
import com.example.demo.service.CarService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/api/cars")
    public ResponseEntity<?> getCars(@RequestHeader(name = "darkmode", required = false) boolean darkMode) {
        List<CarDTO> cars = carService.getAllCars();
        DarkModeDTO darkModeDTO = new DarkModeDTO();
        darkModeDTO.setDarkMode(darkMode);

        Map<String, Object> response = new HashMap<>();
        response.put("darkMode", darkModeDTO.isDarkMode());
        response.put("cars", cars);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/dark-mode")
    public ResponseEntity<?> setDarkMode(@RequestBody DarkModeDTO darkModeDTO, HttpServletResponse response) {
        boolean darkMode = (boolean) darkModeDTO.isDarkMode();

        Cookie cookie = new Cookie("darkmode", String.valueOf(darkMode));
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}
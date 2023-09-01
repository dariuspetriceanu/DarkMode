package com.example.demo.service;

import com.example.demo.dto.CarDTO;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> carDTOs = new ArrayList<>();

        for (Car car : cars) {
            CarDTO carDTO = new CarDTO();
            carDTO.setName(car.getName());
            carDTO.setModel(car.getModel());
            carDTOs.add(carDTO);
        }

        return carDTOs;
    }
}

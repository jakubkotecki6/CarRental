package pl.sda.carrental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.carrental.model.CarRentalModel;
import pl.sda.carrental.service.CarRentalService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarRentalController {
    private final CarRentalService carRentalService;
    @GetMapping("/getCarRental")
    public String getCarRental(){
        CarRentalModel carRental = carRentalService.getCarRental();
        return carRental.toString();
    }
}

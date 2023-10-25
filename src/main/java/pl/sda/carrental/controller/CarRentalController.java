package pl.sda.carrental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.carrental.model.CarRentalModel;
import pl.sda.carrental.service.CarRentalService;

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

package pl.sda.carrental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.carrental.model.CarRentalModel;
import pl.sda.carrental.service.CarRentalService;

@RestController
@RequiredArgsConstructor
public class CarRentalController {
    private final CarRentalService carRentalService;
    @GetMapping("/getCarRental")
    public CarRentalModel getCarRental(){
        return carRentalService.getCarRental();
    }
    @PutMapping("/addCarRental")
    public void putAddCarRental(CarRentalModel carRentalModel){
        carRentalService.saveCarRental(carRentalModel);
    }

    @PutMapping("/editCarRental")
    public void putEditCarRental(CarRentalModel carRentalModel){
        carRentalService.saveCarRental(carRentalModel);
    }

    @DeleteMapping("/deleteCarRental")
    public void deleteCarRental(){
        carRentalService.deleteCarRental();
    }
}

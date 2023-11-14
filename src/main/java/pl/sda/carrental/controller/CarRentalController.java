package pl.sda.carrental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.CarRentalModel;
import pl.sda.carrental.service.CarRentalService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carRental")
public class CarRentalController {
    private final CarRentalService carRentalService;
    @GetMapping
    public CarRentalModel getCarRental(){
        return carRentalService.getCarRental();
    }
    @PostMapping
    public void addCarRental(@RequestBody @Valid CarRentalModel carRentalModel){
        carRentalService.saveCarRental(carRentalModel);
    }

    @PutMapping
    public void editCarRental(@RequestBody CarRentalModel carRentalModel){
        carRentalService.editCarRental(carRentalModel);
    }

    @DeleteMapping
    public void deleteCarRental(){
        carRentalService.deleteCarRental();
    }
}

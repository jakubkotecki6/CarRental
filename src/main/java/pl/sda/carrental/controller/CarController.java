package pl.sda.carrental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.CarModel;
import pl.sda.carrental.service.CarService;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public CarModel getCarModel(Long id){
        return carService.getCarById(id);
    }

    @PostMapping
    public void addCar(CarModel carModel){
        carService.addCar(carModel);
    }

    @PutMapping
    public void editCar(CarModel carModel){
         carService.editCar(carModel);
    }


    @DeleteMapping
    public void deleteCar(Long id){
        carService.deleteCarById(id);
    }
}

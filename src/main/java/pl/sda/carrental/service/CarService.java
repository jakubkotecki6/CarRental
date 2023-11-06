package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.CarModel;
import pl.sda.carrental.repository.CarRepository;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public CarModel getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new ObjectNotFoundInRepositoryException("There is no car with selected id"));
    }

    public void addCar(CarModel car) {
        carRepository.save(car);
    }

    public void editCar(CarModel car) {
        CarModel editedCar = carRepository.findById(car.getId()).orElseThrow(() -> new ObjectNotFoundInRepositoryException(""));
        editedCar.setId(car.getId());
        editedCar.setMake(car.getMake());
        editedCar.setModel(car.getModel());
        editedCar.setBodyStyle(car.getBodyStyle());
        editedCar.setYear(car.getYear());
        editedCar.setColour(car.getColour());
        editedCar.setMileage(car.getMileage());
        editedCar.setStatus(car.getStatus());
        editedCar.setPrice(car.getPrice());

        carRepository.deleteById(car.getId());
        carRepository.save(editedCar);
    }

    public void deleteCarById(Long id) {
        carRepository.findById(id).orElseThrow(() -> new ObjectNotFoundInRepositoryException(""));
        carRepository.deleteById(id);
    }
}

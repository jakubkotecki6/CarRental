package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.CarRentalModel;
import pl.sda.carrental.repository.CarRentalRepository;

@Service
@RequiredArgsConstructor
public class CarRentalService {
    private final CarRentalRepository carRentalRepository;
    public CarRentalModel getCarRental() {
        return carRentalRepository.findAll().stream().findFirst().orElseThrow(RuntimeException::new);
    }

    public void saveCarRental(CarRentalModel carRental) {
        carRentalRepository.save(carRental);
    }

    public void deleteCarRental() {
        carRentalRepository.deleteAll();
    }
}

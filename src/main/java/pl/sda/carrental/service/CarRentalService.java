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
        return (CarRentalModel) carRentalRepository.findAll();
    }
}

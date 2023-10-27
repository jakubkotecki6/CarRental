package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.CarRentalModel;
import pl.sda.carrental.repository.CarRentalRepository;

@Service
@RequiredArgsConstructor
public class CarRentalService {
    private final CarRentalRepository carRentalRepository;

    public CarRentalModel getCarRental() {
        return carRentalRepository.findAll().stream().findFirst().orElseThrow(() -> new ObjectNotFoundInRepositoryException("There is no car rental company"));
    }

    public void saveCarRental(CarRentalModel carRental) {
        carRentalRepository.save(carRental);
    }

    public void editCarRental(CarRentalModel carRentalModel) {
        CarRentalModel edited = carRentalRepository.findAll().stream().findFirst().orElseThrow(() -> new ObjectNotFoundInRepositoryException("There is no car rental company to edit"));
        edited.setId(carRentalModel.getId());
        edited.setName(carRentalModel.getName());
        edited.setAddress(carRentalModel.getAddress());
        edited.setOwner(carRentalModel.getOwner());
        edited.setLogo(carRentalModel.getLogo());

        carRentalRepository.deleteAll();

        carRentalRepository.save(edited);
    }

    public void deleteCarRental() {
        carRentalRepository.findAll().stream().findFirst().orElseThrow(() -> new ObjectNotFoundInRepositoryException("There is no car rental company"));

        carRentalRepository.deleteAll();
    }
}

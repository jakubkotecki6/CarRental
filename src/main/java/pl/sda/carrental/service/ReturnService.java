package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.carrental.exceptionHandling.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.exceptionHandling.ReturnAlreadyExistsForReservationException;
import pl.sda.carrental.model.DTO.ReturnDTO;
import pl.sda.carrental.model.Employee;
import pl.sda.carrental.model.Reservation;
import pl.sda.carrental.model.Returnal;
import pl.sda.carrental.repository.EmployeeRepository;
import pl.sda.carrental.repository.ReservationRepository;
import pl.sda.carrental.repository.ReturnRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ReturnService {
    private final ReservationRepository reservationRepository;
    private final ReturnRepository returnRepository;
    private final EmployeeRepository employeeRepository;

    /**
     * Gets all Return Objects
     *
     * @return List of all Return objects
     */
    public List<Returnal> getAllReturnals() {
        return returnRepository.findAll();
    }

    /**
     * Saves Return Object
     *
     * @param returnDTO Return object which you want to add to repository
     * @return saves in returnRepository
     */
    @Transactional
    public Returnal saveReturn(ReturnDTO returnDTO) {
        List<Long> reservationsIds = returnRepository.findReturnsWithReservationId(returnDTO.reservationId());
        if(!reservationsIds.isEmpty()) {
            throw new ReturnAlreadyExistsForReservationException("Return already exists for reservation with id " + returnDTO.reservationId());
        }

        Returnal returnal = new Returnal();
        updateReturnalDetails(returnDTO, returnal);

        return returnRepository.save(returnal);
    }

    /**
     * Updates Return Object found by ID with new one
     *
     * @param id ID of edited object
     * @param returnDTO Replacement object
     * @return saves in returnRepository
     */
    @Transactional
    public Returnal editReturnal(Long id, ReturnDTO returnDTO) {
        Returnal returnal = returnRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No returnal under ID #" + id));
        updateReturnalDetails(returnDTO, returnal);

        return returnRepository.save(returnal);
    }

    /**
     *
     *
     * @param returnDTO
     * @param returnalToSave
     */
    private void updateReturnalDetails(ReturnDTO returnDTO, Returnal returnalToSave) {

        returnalToSave.setReturnDate(returnDTO.returnDate());
        returnalToSave.setComments(returnDTO.comments());
        returnalToSave.setUpcharge(returnDTO.upcharge());

        Employee employeeFromRepository = employeeRepository.findById(returnDTO.employee())
                        .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No employee under ID #" + returnDTO.employee()));

        returnalToSave.setEmployee(employeeFromRepository);

        Reservation reservationFromRepository = reservationRepository.findById(returnDTO.reservationId())
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Reservation with id "
                        + returnDTO.reservationId() + " not found"));

        returnalToSave.setReservation(reservationFromRepository);
    }

    /**
     * deletes selected by ID object
     *
     * @param id ID of deleted object
     */
    @Transactional
    public void deleteReturnal(Long id) {
        returnRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No returnal under ID #" + id));
        returnRepository.deleteById(id);
    }
}

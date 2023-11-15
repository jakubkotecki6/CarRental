package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.ReservationModel;
import pl.sda.carrental.repository.ReservationRepository;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationModel saveReservation(ReservationModel reservation) {
        return reservationRepository.save(reservation);
    }

}

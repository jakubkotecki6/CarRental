package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.BranchModel;
import pl.sda.carrental.model.ReservationModel;
import pl.sda.carrental.repository.BranchRepository;
import pl.sda.carrental.repository.ReservationRepository;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BranchRepository branchRepository;

    public ReservationModel saveReservation(ReservationModel reservation) {
        setStartBranchIfExists(reservation);
        setEndBranchIfExists(reservation);

        return reservationRepository.save(reservation);
    }


    private void setEndBranchIfExists(ReservationModel reservation) {
        String endBranchName = reservation.getEndBranch().getName();
        BranchModel endBranch = getCompanyBranchModel(endBranchName);
        reservation.setEndBranch(endBranch);
    }

    private void setStartBranchIfExists(ReservationModel reservation) {
        String startBranchName = reservation.getStartBranch().getName();
        BranchModel startBranch = getCompanyBranchModel(startBranchName);
        reservation.setStartBranch(startBranch);
    }

    private BranchModel getCompanyBranchModel(String branchName) {
        return branchRepository.findByName(branchName)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("Branch with name "
                        + branchName + " not found"));
    }

}

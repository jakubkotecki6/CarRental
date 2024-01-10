package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.exceptionHandling.ObjectAlreadyExistsException;
import pl.sda.carrental.exceptionHandling.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.Revenue;
import pl.sda.carrental.repository.BranchRepository;
import pl.sda.carrental.repository.RevenueRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RevenueService {
    private final RevenueRepository revenueRepository;
    private final BranchRepository branchRepository;

    public List<Revenue> getRevenue() {
        return revenueRepository.findAll();
    }

    public Revenue addRevenue(Revenue revenue) {
        if (!revenueRepository.findAll().isEmpty()) {
            throw new ObjectAlreadyExistsException("There already is Revenue object for this application!");
        }
        return revenueRepository.save(revenue);
    }


    public Revenue editRevenue(Long id, Revenue revenue) {
        Revenue foundRevenue = revenueRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No revenue under ID #" + id + "!"));

        foundRevenue.setTotalAmount(revenue.getTotalAmount());

        return revenueRepository.save(foundRevenue);
    }

    public void updateRevenue(Long id, BigDecimal rentalAmount) {
        Revenue revenue = revenueRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundInRepositoryException("No revenue under ID #" + id + "!"));
        BigDecimal updatedRevenue = revenue.getTotalAmount().add(rentalAmount);
        revenue.setTotalAmount(updatedRevenue);

        revenueRepository.save(revenue);
    }

    public void deleteRevenue(Long id) {
        revenueRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No revenue under ID #" + id + "!"));

        revenueRepository.deleteAll();
    }
}

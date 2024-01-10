package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.exceptionHandling.ObjectAlreadyExistsException;
import pl.sda.carrental.exceptionHandling.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.Revenue;
import pl.sda.carrental.repository.RevenueRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RevenueService {
    private final RevenueRepository revenueRepository;

    public List<Revenue> getRevenue() {
        return revenueRepository.findAll();
    }

    public Revenue addRevenue(Revenue revenue) {
        if(!revenueRepository.findAll().isEmpty()) {
            throw new ObjectAlreadyExistsException("There already is Revenue object for this application!");
        }
        return revenueRepository.save(revenue);
    }


    public Revenue editRevenue(Revenue revenue) {
        Revenue edit = revenueRepository.findAll().stream().findAny()
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("There is no revenue at this moment!"));

        edit.setTotalAmount(revenue.getTotalAmount());

        return revenueRepository.save(edit);
    }

    public void updateRevenue(BigDecimal rentalAmount) {
        Revenue revenue = revenueRepository.findAll().stream().findAny().orElse(null);
        if (revenue == null) {
            revenue = new Revenue();
            revenue.setTotalAmount(rentalAmount);
        } else {
            BigDecimal updatedRevenue = revenue.getTotalAmount().add(rentalAmount);
            revenue.setTotalAmount(updatedRevenue);
        }

        revenueRepository.save(revenue);
    }

    public void deleteRevenue() {
        revenueRepository.findAll().stream().findAny()
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No revenue to remove!"));

        revenueRepository.deleteAll();
    }
}

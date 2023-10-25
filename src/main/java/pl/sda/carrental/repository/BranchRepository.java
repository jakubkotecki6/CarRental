package pl.sda.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.carrental.model.BranchModel;

public interface BranchRepository extends JpaRepository<BranchModel, Long> {
}

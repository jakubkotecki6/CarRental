package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.BranchModel;
import pl.sda.carrental.repository.BranchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchModel addBranch(BranchModel branch) {
        return branchRepository.save(branch);
    }

    public List<BranchModel> getAllBranches() {
        return branchRepository.findAll();
    }

    public void removeBranch(BranchModel branchModel) {
        branchRepository.delete(branchModel);
    }
}

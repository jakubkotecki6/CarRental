package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.BranchModel;
import pl.sda.carrental.repository.BranchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public void addBranch(BranchModel branch) {
        branchRepository.save(branch);
    }

    public List<BranchModel> getAllBranches() {
        return branchRepository.findAll();
    }

    /*  WARNING     WARNING     WARNING     WARNING     WARNING     WARNING     */
    /*  Cannot use findById() for branches that are not assigned to CarRental   */
    /*  =====================================================================   */
    /*  Instead i create and delete it from CarRental perspective, where i am
    *   able to assign it to CarRental  */
    public void removeBranch(Long id) {
        branchRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No branch under  ID #" + id));
        branchRepository.deleteById(id);
    }

    public BranchModel editBranch(Long id, BranchModel branchModel) {
        BranchModel found = branchRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No branch under  ID #" + id));

        found.setAddress(branchModel.getAddress());
        found.setName(branchModel.getName());

        branchRepository.deleteById(id);

        return branchRepository.save(found);

    }

    // how to handle exceptions better
    public BranchModel getById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No branch under  ID #" + id));
    }
}

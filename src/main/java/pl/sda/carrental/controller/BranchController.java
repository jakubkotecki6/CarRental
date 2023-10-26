package pl.sda.carrental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.BranchModel;
import pl.sda.carrental.service.BranchService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/branches")
public class BranchController {
    private final BranchService branchService;

    @GetMapping
    public List<BranchModel> getBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public BranchModel getById(@PathVariable Long id) {
        return branchService.getById(id);
    }

    @PostMapping
    public void addBranch(@RequestBody BranchModel branch) {
        branchService.addBranch(branch);
    }

    @PutMapping("/{id}")
    public BranchModel modifyBranch(@PathVariable Long id, @RequestBody BranchModel branch) {
            return branchService.editBranch(id, branch);
    }

    @DeleteMapping("/{id}")
    public void removeBranch(@PathVariable Long id) {
            branchService.removeBranch(id);
    }
}

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
    public ResponseEntity<List<BranchModel>> getBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @PostMapping
    public ResponseEntity<Void> addBranch(@RequestBody BranchModel branch) {
        if(branchService.addBranch(branch).equals(branch)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Void> modifyBranch(@RequestBody BranchModel branch) {
        Optional<BranchModel> foundBranch = branchService.getAllBranches().stream()
                .filter(branchToModify -> branchToModify.getBranch_id() == branch.getBranch_id()).findFirst();
        if(foundBranch.isPresent()) {
            branchService.removeBranch(foundBranch.get());
            branchService.addBranch(foundBranch.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBranch(@PathVariable Long id) {
        Optional<BranchModel> found = branchService.getAllBranches().stream().filter(branch -> branch.getBranch_id() == id).findFirst();
        if(found.isPresent()) {
            branchService.removeBranch(found.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

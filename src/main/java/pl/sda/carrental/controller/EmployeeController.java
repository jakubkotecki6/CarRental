package pl.sda.carrental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.EmployeeModel;
import pl.sda.carrental.service.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeModel>> getEmployees() {
        try {
            List<EmployeeModel> allEmployees = employeeService.getAllEmployees();
            return ResponseEntity.ok(allEmployees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> addEmployee(@RequestBody EmployeeModel employee) {
        try {
            EmployeeModel addedEmployee = employeeService.addEmployee(employee);
            return ResponseEntity.ok(addedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeModel> editEmployee(@PathVariable Long id, @RequestBody EmployeeModel employee) {
        try {
            EmployeeModel edited = employeeService.editEmployee(id, employee);
            return ResponseEntity.ok(edited);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try{
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

package pl.sda.carrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.exceptionHandling.ObjectNotFoundInRepositoryException;
import pl.sda.carrental.model.Employee;
import pl.sda.carrental.repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    /**
     * Method returns all Employees
     *
     * @return List of all Employees
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    /**
     * Adds new employee to repository
     *
     * @param employee Employee which you want to add to repository
     * @return  Saves employee in employeeRepository
     */
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Replaces old employee with new updated one
     *
     * @param id Id of employee which you want to edit
     * @param employee Employee which will replace old record
     * @return Saves employee in employeeRepository
     */
    public Employee editEmployee(Long id, Employee employee) {
        Employee edit = employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No employee under ID #" + id));

        edit.setName(employee.getName());
        edit.setSurname(employee.getSurname());
        edit.setPosition(employee.getPosition());
        edit.setBranch(employee.getBranch());
        employeeRepository.deleteById(id);

        return employeeRepository.save(edit);
    }

    /**
     * deletes specified employee
     *
     * @param id Id of employee which you want to delete
     */
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundInRepositoryException("No employee under that ID!"));
        employeeRepository.deleteById(id);
    }
}

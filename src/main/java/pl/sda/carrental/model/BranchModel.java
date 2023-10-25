package pl.sda.carrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "adress")
    private String address;
    @ManyToMany
    private List<EmployeeModel> employees;
    @ManyToMany
    private List<CarModel> availableCars;
}

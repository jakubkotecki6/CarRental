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
@Table(name = "car_rental")
public class CarRentalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String domain;
    private String address;
    private String owner;
    private String logo;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "carRentalModel")
    private List<BranchModel> branches;*/
}

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
public class CarRentalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "domain")
    private String domain;
    @Column(name = "address")
    private String address;
    @Column(name = "owner")
    private String owner;
    @Column(name = "logo")
    private String logo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carRentalModel")
    private List<BranchModel> branches;
}

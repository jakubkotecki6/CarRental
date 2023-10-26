package pl.sda.carrental.model;

import jakarta.persistence.*;
import lombok.*;
import pl.sda.carrental.model.enums.Position;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;
    private String name;
    private String surname;
    private Position position;
//    @ManyToOne
//    private BranchModel branch;

}

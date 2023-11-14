package pl.sda.carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "name cannot be null")
    private String surname;
    @NotNull(message = "name cannot be null")
    private String email;
    @NotNull(message = "name cannot be null")
    private String address;

}

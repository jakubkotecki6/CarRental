package pl.sda.carrental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.carrental.model.enums.Status;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long car_id;
    private String make;
    private String model;
    private String bodyStyle;
    private int year;
    private String colour;
    private int mileage;
    private Status status;
    @DecimalMin(value = "1.00", message = "Price must be grater than 1.00")
    @DecimalMax(value = "10000.00", message = "Price must be lesser than 10000.00")
    @Digits(integer = 7, fraction = 2, message = "Price must have up to 7 digits in total and 2 decimal places")
    private BigDecimal price;

}

package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;


    @NotEmpty(message = "Position Cannot be null.")
    @Column(columnDefinition = "varchar(10) not null ")
    private String position;


    //- Must be a non-negative decimal number.
    @NotEmpty(message = "Salary Cannot be null.")
    @Positive(message = "Salary Must be a non-negative decimal number. ")
    @Column(columnDefinition = "int not null ")
   // @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be a non-negative value")
    private Integer salary;



    @OneToOne(cascade = CascadeType.ALL,mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private User user;








}

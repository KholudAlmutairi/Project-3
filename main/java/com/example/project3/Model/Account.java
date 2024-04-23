package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Account Number Cannot be null")
    // Must follow a specific format (e.g., "XXXX-XXXX-XXXX-XXXX")
    //@Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "Invalid account number format")
    private Integer accountNumber;



    //Must be a non-negative decimal number.
    @Positive(message = "Balance Must be a non-negative decimal number. ")
    @NotNull(message = "Balance Number Cannot be null")
    @Column(columnDefinition = "double not null ")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be a non-negative value")
    private double balance;

    @NotNull(message = "isActive Cannot be null")
    @Column(columnDefinition ="boolean default false")
    private boolean isActive=false;
    private double amount;



    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;










}

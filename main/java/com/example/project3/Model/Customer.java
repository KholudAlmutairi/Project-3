package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Customer {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Size(min = 10,max = 10,message = "phone number must be 10 digit")
    @Column(columnDefinition = "varchar(10) not null ")
   // @Pattern(regexp = "^05\\d{8}$", message = "Invalid phone number format")
    private String phoneNumber;
    //Must start with "05


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private User user;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;












}

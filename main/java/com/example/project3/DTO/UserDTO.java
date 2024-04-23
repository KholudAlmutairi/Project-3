package com.example.project3.DTO;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class UserDTO {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(15) not null unique")
    private  String username;

    @Column(columnDefinition = "varchar(15) not null ")
    private  String password;

    @Column(columnDefinition = "varchar(15) not null ")
    private String name;

    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    //Must be either "CUSTOMER" , "EMPLOYEE" or "ADMIN" only.

    private String role;





}

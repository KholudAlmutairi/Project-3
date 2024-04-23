package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = " Username Cannot be null.")
    @Size(max = 10,min = 4,message = "Length must be between 4 and 10 characters")
    //Must be unique.
    @Column(columnDefinition = "varchar(15) not null unique")
    private  String username;

    @NotEmpty(message = "Password Cannot be null.")
    @Size(min = 7,message = "Password Length must be at least 6 characters.")
    @Column(columnDefinition = "varchar(15) not null ")
    private  String password;

    @NotEmpty(message = "Name Cannot be null.")
    @Size(min = 2,max = 20,message = "Name Length must be between 2 and 20 characters")
    @Column(columnDefinition = "varchar(15) not null ")
    private String name;

    @Email(message = "Invalid email format")
    //Must be unique.
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    //Must be either "CUSTOMER" , "EMPLOYEE" or "ADMIN" only.
    //private Role role;
    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$")
    private String role;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Employee employee;


    @OneToOne
    @MapsId
    @JsonIgnore
    private Customer customer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

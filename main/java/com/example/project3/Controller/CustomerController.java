package com.example.project3.Controller;

import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CustomerDTO customer) {
        customerService.register(customer);
        return ResponseEntity.status(200).body("Customer registered successfully");
    }

    @GetMapping("/get")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }



    @PostMapping("/add")
    public Customer createCustomer(@RequestBody @AuthenticationPrincipal Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody @AuthenticationPrincipal Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }
}

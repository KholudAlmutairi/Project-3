package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final AuthRepository authRepository;

  public void register(CustomerDTO customerDTO){

    String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
    User user = new User(null, customerDTO.getUsername(), hashPassword, customerDTO.getName(), customerDTO.getEmail(), "CUSTOMER", null, null);
    authRepository.save(user);

    Customer customer = new Customer(null, customerDTO.getPhoneNumber(), user, null);
    customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers() {//admin
    return customerRepository.findAll();
  }

  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public Customer updateCustomer(Integer id, Customer customer) {
    Customer c = customerRepository.findCustomersById(id);
      if(c==null)
          throw new ApiException("Customer not found with id: ");

       c.setPhoneNumber(customer.getPhoneNumber());
       return customerRepository.save(c);
  }

  public void deleteCustomer(Integer id) { //admin
    Customer c = customerRepository.findCustomersById(id);
    if (c == null) {
      throw new ApiException("Customer not found with id: ");
    }
    customerRepository.delete(c);

  }




}

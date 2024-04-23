package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;
    private final AccountRepository accountRepository;



    public void register(EmployeeDTO employeeDTO){

        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        User user = new User(null, employeeDTO.getUsername(), hashPassword, employeeDTO.getName(), employeeDTO.getEmail(), "EMPLOYEE", null, null);
        authRepository.save(user);

        Employee employee = new Employee(null, employeeDTO.getPosition(),employeeDTO.getSalary(), user);//employeeDTO.getSalary()
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {//admin
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
      return employeeRepository.findEmployeesById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        Employee e = employeeRepository.findEmployeesById(id);
         if(e==null)
            throw new ApiException("Employee not found with id: ");

            e.setPosition(employee.getPosition());
            e.setSalary(employee.getSalary());
            return employeeRepository.save(e);
    }

    public void deleteEmployee(Integer id) {
        Employee e = employeeRepository.findEmployeesById(id);
        if(e==null)
            throw new ApiException("Employee not found with id: ");

        employeeRepository.delete(e);
        }


    public List<User> userList(){
        return authRepository.findAll();
    }

    public void blockAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId);
        account.setActive(account.isActive());
                //setIsActive(Boolean.FALSE);
        accountRepository.save(account);
    }


    public void activeAccount(Integer accountId ){

        Account account = accountRepository.findAccountById(accountId);

        account.setActive(account.isActive());
                //setIsActive(Boolean.TRUE);

        accountRepository.save(account);

    }



    }









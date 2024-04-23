package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid @AuthenticationPrincipal EmployeeDTO employee){
        employeeService.register(employee);
        return ResponseEntity.status(200).body("Employee registered successfully");
    }


    @PostMapping("/add")
    public Employee createEmployee(@RequestBody @Valid @AuthenticationPrincipal Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("update/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody @AuthenticationPrincipal Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("delete/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);

    }


    @PutMapping("/active")
    public ResponseEntity activeAccount(@AuthenticationPrincipal User user , Integer accountId ){
        employeeService.activeAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("activated"));
    }



    @GetMapping("/list-users")
    public ResponseEntity listUsers(){
        return ResponseEntity.status(200).body(employeeService.userList());

    }


    @PutMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user , @PathVariable Integer accountId ){
        employeeService.blockAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("account blocked"));
    }
    }

package com.example.project3.Controller;

import com.example.project3.Model.User;
import com.example.project3.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid@ AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body("Welcome back!");

    }


    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody @Valid @AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body("Done successfully logout!");
    }


    @GetMapping("/get")
    public ResponseEntity getAllUsers(){//admin
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }




}

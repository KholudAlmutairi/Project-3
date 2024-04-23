package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void registerCustomer(User user){
        user.setRole("CUSTOMER");
       // user.setRole("EMPLOYEE");
        String hashPassword= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }


    public void registerEmployee(User user){
        //user.setRole("CUSTOMER");
        user.setRole("EMPLOYEE");
        String hashPassword= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }


    public List<User> getAllUsers() {// admin
        return authRepository.findAll();
    }



    public User getUserById(Integer userId){
        return authRepository.findUserById(userId);
    }


    public void deleteUser(String username1,String username2) {//admin
        User u = authRepository.findUserByUsername(username1);
        if (!u.getRole().equalsIgnoreCase("ADMIN")) {
            throw new ApiException("Admin cannot be deleted");
        }
        User u2=authRepository.findUserByUsername(username1);
        authRepository.delete(u2);
    }



    public void updateUser(Integer id,User user) {
        user.setRole("CUSTOMER");
        User u = authRepository.findUserById(id);

        if (u == null) {
            throw new ApiException("Wrong id");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        authRepository.save(u);


    }

}

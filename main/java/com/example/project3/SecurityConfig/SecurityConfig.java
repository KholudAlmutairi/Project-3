package com.example.project3.SecurityConfig;

import com.example.project3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService;

    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;


    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
      http.csrf().disable()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
              .and()
              .authenticationProvider(daoAuthenticationProvider())
              .authorizeHttpRequests()
              .requestMatchers("api/v1/customer/register").hasAuthority("CUSTOMER")
              .requestMatchers("/api/v1/employee/register").hasAuthority("EMPLOYEE")
              .requestMatchers("/api/v1/customer/get").hasAuthority("ADMIN")
              .requestMatchers("/api/v1/accounts/get-all").hasAuthority("ADMIN")
              .requestMatchers("/api/v1/accounts/delete/").hasAuthority("CUSTOMER")
              .requestMatchers("/api/v1/accounts/add").hasAuthority("CUSTOMER")
              .requestMatchers("/api/v1/accounts/update/").hasAuthority("CUSTOMER")
              .requestMatchers("api/v1/accounts/details").hasAuthority("CUSTOMER")
              .requestMatchers("/api/v1/accounts/deposit/").hasAuthority("CUSTOMRT")
              .requestMatchers("/api/v1/accounts/get-all").hasAuthority("ADMIN")
              .requestMatchers("/api/v1/employee/active").hasAuthority("EMPLOYEE")
              .requestMatchers("/api/v1/employee/delete/").hasAuthority("EMPLOYEE")
              .requestMatchers("/api/v1/employee/block/").hasAuthority("EMPLOYEE")
              .requestMatchers("/api/v1/employee/update/").hasAuthority("EMPLOYEE")
              .requestMatchers("api/v1/employee/list-users").hasAuthority("ADMIN")
              .anyRequest().authenticated()
              .and()
              .logout().logoutUrl("/api/v1/auth/logout")
              .deleteCookies("JSESSIONID")
              .invalidateHttpSession(true)
              .and()
              .httpBasic();
      return http.build();


    }


}
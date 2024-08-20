package com.sumergeTask.sumergeTask.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf(AbstractHttpConfigurer::disable)
               .addFilterBefore(new ValidationFilter(), UsernamePasswordAuthenticationFilter.class)
               .authorizeHttpRequests(authz -> authz
                       .requestMatchers("viewAll","view/","viewPag","add").permitAll()
                       .anyRequest().authenticated())
               .httpBasic(Customizer.withDefaults());

       return http.build();
   }

   @Bean
    public UserDetailsService userDetailsService() {
       UserDetails user = User.builder()
               .username("user")
               .password("$2a$12$WQmPwQSEi6A1HAENCasNGup9N/M8shxQEHmlA7km.qgIfTTjgQXW2")
               .build();
       UserDetails admin = User.builder()
               .username("admin")
               .password("$2a$12$a2SCxUeQkt2jc1kU25BOGemeL5x5HaMPbFMh9IWegdEpU6oByl8Mi")
               .build();
       return new InMemoryUserDetailsManager(user, admin);
   }

   @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
}

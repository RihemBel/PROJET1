package com.example.ecommerce;

import com.example.ecommerce.Service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class EcommerceApplication  implements CommandLineRunner {

    private final RoleService roleService;

    public EcommerceApplication(RoleService roleService) {
        this.roleService = roleService;
    }

    public static void main(String[] args) {

        SpringApplication.run(EcommerceApplication.class, args);

    }


    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

        roleService.createRoles();
    }

}


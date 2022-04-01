package com.example.ecommerce.Security.auth;

import com.example.ecommerce.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;


@Component("userDetailsService")
public class ApplicationUserService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserService.class);

    private final UserRepository userRepository;

    public ApplicationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public  UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("User with email " + login + " was not found in the database")));
    }
}

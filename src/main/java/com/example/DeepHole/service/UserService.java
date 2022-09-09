package com.example.DeepHole.service;

import com.example.DeepHole.models.SecurityUser;
import com.example.DeepHole.models.User;
import com.example.DeepHole.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);
        if( user.isPresent()){
            return new SecurityUser(user.get());
        }else {
            throw new UsernameNotFoundException("username not found");
        }

    }


}

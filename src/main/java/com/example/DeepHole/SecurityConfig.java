package com.example.DeepHole;

import com.example.DeepHole.models.SecurityUser;
import com.example.DeepHole.models.User;
import com.example.DeepHole.repository.UserRepository;
import com.example.DeepHole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    UserRepository repository;
    @Autowired
    UserService service;




    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain authentication(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(ar -> ar.anyRequest().permitAll())
                .build();
    }

//    @Bean
//    ApplicationListener<ApplicationReadyEvent> ready(){
//        return event -> {
//            repository.save(new User("David kamau", "kamaa@kamaa.com", encoder().encode("kamaa"), true));
//            System.out.println(repository.findAll());
//        };
//    }
//
    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(this.encoder());
        authenticationProvider.setUserDetailsService(service);
        return authenticationProvider;

    }

    @Bean
   UserDetailsService userDetailsService(){
        User user = new User("kamaa", "kamaa@kamaa.com", encoder().encode("kamaa"), true);
        SecurityUser securityUser = new SecurityUser(user);
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return securityUser;
            }
        };
    }


}

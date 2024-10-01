package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService inMemory(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager inMemory = new InMemoryUserDetailsManager();

        inMemory.createUser(
            User.withUsername("user")
                // .password("{noop}123456")
                .password(passwordEncoder.encode("123456"))
                // .authorities("ROLE_USER")
                .roles("USER") // C'est pareil que ROLE_USER
                .build()
        );

        inMemory.createUser(
            User.withUsername("admin")
                .password(passwordEncoder.encode("123456$"))
                // .authorities("ROLE_ADMIN")
                .roles("ADMIN") // C'est pareil que ROLE_USER
                .build()
        );

        return inMemory;
    }

}

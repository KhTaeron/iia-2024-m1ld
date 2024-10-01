package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean // Configuration des accès (Authorization)
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/api/fournisseur/**").hasAuthority("ROLE_ADMIN");
            authorize.requestMatchers("/api/fournisseur/**").hasRole("ADMIN");

            authorize.requestMatchers("/**").authenticated(); 
        });

        http.formLogin(Customizer.withDefaults());

        return http.build();
    }
    
    @Bean // Configuration de l'encodeur de mot de passe
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // Configuration des users (Authentication)
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

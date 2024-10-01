package fr.formation.api;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.exception.UserNotFoundException;
import fr.formation.model.User;
import fr.formation.repo.UserRepository;
import fr.formation.request.AuthRequest;
import fr.formation.request.SubscribeRequest;
import fr.formation.response.AuthResponse;
import fr.formation.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Log4j2
public class UserApiController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        log.debug("Authenticating requested for user {} ...", request.getUsername());

        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            authentication = this.authenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("User {} authenticated! Generating token ...", request.getUsername());
            
            String token = JwtUtil.generate(this.repository.findByUsername(request.getUsername()).orElseThrow(UserNotFoundException::new));

            log.debug("Token *** generated!");

            return AuthResponse.builder()
                .success(true)
                .token(token)
                .build()
            ;
        }

        catch (BadCredentialsException e) {
            return AuthResponse.builder()
                .success(false)
                .build()
            ;
        }
    }
    
    @PostMapping("/subscribe")
    @ResponseStatus(HttpStatus.CREATED)
    public String subscribe(@RequestBody SubscribeRequest request) {
        log.debug("User {} subscribing ...", request.getUsername());
        
        User user = new User();
        
        BeanUtils.copyProperties(request, user);
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        
        this.repository.save(user);

        log.debug("User {} subscribed!", request.getUsername());

        return user.getId();
    }
}

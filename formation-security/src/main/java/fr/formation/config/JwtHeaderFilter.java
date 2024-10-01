package fr.formation.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        // Si pas de authHeader => Pas d'authentification
        // Si authHeader == Bearer admin => Authentifier en tant qu'admin
        // Si authHeader == Bearer ??? => Authentifier en tant qu'utilisateur

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            "username",
            "username",
            List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN")
            )
        );

        // On ajoute l'authentification au contexte de Sécurité de Spring Security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Passer à la suite de la chaine de filtres
        filterChain.doFilter(request, response);
    }
}

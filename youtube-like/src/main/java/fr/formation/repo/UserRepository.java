package fr.formation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByUsername(String username);
}

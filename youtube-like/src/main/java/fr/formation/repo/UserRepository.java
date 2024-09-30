package fr.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}

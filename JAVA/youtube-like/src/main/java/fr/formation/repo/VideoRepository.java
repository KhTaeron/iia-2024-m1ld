package fr.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Video;

public interface VideoRepository extends JpaRepository<Video, String> {
    
}

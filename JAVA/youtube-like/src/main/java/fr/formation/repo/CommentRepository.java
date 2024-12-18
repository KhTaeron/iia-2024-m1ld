package fr.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {
    public List<Comment> findAllByVideoId(String videoId);
}

package fr.formation.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter @Setter
public class Comment {
    @Id
    @UuidGenerator
    @Column(name = "com_id")
    private String id;

    @Column(name = "com_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "com_date", nullable = false)
    private LocalDateTime date;

    @JoinColumn(name = "com_video_id", nullable = false)
    @ManyToOne
    private Video video;

    @JoinColumn(name = "com_user_id", nullable = false)
    @ManyToOne
    private User user;
}

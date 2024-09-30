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
@Table(name = "video")
@Getter @Setter
public class Video {
    @Id
    @UuidGenerator
    @Column(name = "vid_id")
    private String id;
    
    @Column(name = "vid_name", length = 100, nullable = false)
    private String name;

    @Column(name = "vid_desc", columnDefinition = "TEXT")
    private String description;

    @Column(name ="vid_date", nullable = false)
    private LocalDateTime date;

    @JoinColumn(name ="vid_owner_id", nullable = false)
    @ManyToOne
    private User owner;
    
    @Column(name ="vid_owner_id", insertable = false, updatable = false)
    private String ownerId;
}

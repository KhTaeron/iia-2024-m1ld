package fr.formation.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Video {
    private String id;
    private String name;
    private String description;
    private LocalDateTime date;
    private User owner;
}

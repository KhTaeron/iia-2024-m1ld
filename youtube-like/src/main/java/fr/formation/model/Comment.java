package fr.formation.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Comment {
    private String id;
    private String content;
    private LocalDateTime date;
    private Video video;
    private User user;
}

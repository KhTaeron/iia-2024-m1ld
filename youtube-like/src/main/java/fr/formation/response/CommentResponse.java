package fr.formation.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class CommentResponse {
    private String id;
    private String content;
    private LocalDateTime date;
    private String userId;
    private String userName;
}

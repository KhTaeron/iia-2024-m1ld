package fr.formation.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class VideoResponse {
    private String id;
    private String name;
    private String description;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private LocalDateTime date;
    
    private String ownerId;
}

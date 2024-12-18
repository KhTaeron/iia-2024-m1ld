package fr.formation.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@SuperBuilder
public class VideoByIdResponse extends VideoResponse {
    private String description;
}

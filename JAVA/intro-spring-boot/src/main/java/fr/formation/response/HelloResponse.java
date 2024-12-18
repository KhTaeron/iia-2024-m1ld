package fr.formation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class HelloResponse {
    private String user;
}

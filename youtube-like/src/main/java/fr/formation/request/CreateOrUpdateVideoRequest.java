package fr.formation.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrUpdateVideoRequest {
    private String name;
    private String description;
}

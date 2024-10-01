package fr.formation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class FournisseurResponse {
    private String id;
    private String name;
}

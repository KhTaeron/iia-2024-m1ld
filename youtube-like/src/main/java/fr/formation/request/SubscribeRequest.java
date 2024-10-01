package fr.formation.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubscribeRequest {
    private String name;
    private String username;
    private String password;
}

package fr.formation.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utilisateur")
@Getter @Setter
public class Utilisateur {
    @Id
    @UuidGenerator
    private String id;

    private String username;

    @Column(length = 500)
    private String password;
}

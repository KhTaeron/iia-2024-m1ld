package fr.formation.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Commentaire {
    @Id
    @UuidGenerator
    private String id;
    
    private String texte;
    private int note;

    @ManyToOne
    private Produit produit;
}

package fr.formation.model;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fournisseur")
@Getter @Setter
public class Fournisseur {
    @Id
    @UuidGenerator
    @Column(name = "fou_id")
    private String id;
    
    @Column(name = "fou_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "fournisseur")
    private List<Produit> produits;
}

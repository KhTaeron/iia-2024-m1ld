package fr.formation.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity // Obligatoire pour toute Entité
@Table(name = "produit")
@Getter @Setter
public class Produit {

    @Id // Obligatoire pour chaque Entité - l'identifiant unique
    @UuidGenerator
    @Column(name = "pro_id")
    private String id;

    // Pour faire de l'Auto Incrément sur nos clés primaires
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    // private int id;

    // Le champ "name" String de taille 100 max pas nullable
    @Column(name = "pro_name", length = 100, nullable = false)
    private String name;
    
    // Le champ "price" BigDecimal pas nullable
    @Column(name = "pro_price", nullable = false)
    private BigDecimal price;
    
    // Le champ "date" LocalDateTime pas nullable
    @Column(name = "pro_date", nullable = false)
    private LocalDateTime date;

    @JoinColumn(name = "pro_fournisseur_id")
    @ManyToOne
    private Fournisseur fournisseur;

    @Transient // Ignorer l'attribut en persistance
    private boolean test;
}

package fr.formation.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "[user]")
@Getter @Setter
public class User {
    @Id
    @UuidGenerator
    @Column(name = "usr_id")
    private String id;
    
    @Column(name = "usr_name", length = 50, nullable = false)
    private String name;
}

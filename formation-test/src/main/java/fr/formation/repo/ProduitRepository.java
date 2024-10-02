package fr.formation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.formation.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository // On demande à Spring de la gérer en tant que Repository
public class ProduitRepository {
    @PersistenceContext // On demande à Spring de nous injecter un EntityManager
    private EntityManager em;

    public List<Produit> findAll() {
        return this.em
            .createQuery("select p from Produit p", Produit.class)
            .getResultList()
        ;
    }

    public Optional<Produit> findById(String id) {
        try {
            Produit produit = this.em.find(Produit.class, id);

            return Optional.ofNullable(produit);
        }

        catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Transactional // Spring qui va gérer la transaction - ouverture, commit ou rollback si erreur
    public Produit save(Produit produit) {
        this.em.persist(produit);

        // // Démarrage de la transaction
        // this.em.getTransaction().begin();
        
        // try {
        //     this.em.persist(produit);
            
        //     // Appliquer la transaction
        //     this.em.getTransaction().commit();
        // }

        // catch (Exception ex) {
        //     // Annuler la transaction
        //     this.em.getTransaction().rollback();
        // }

        return produit;
    }
}

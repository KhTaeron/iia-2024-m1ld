package fr.formation.repo;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.formation.model.Fournisseur;

@DataJpaTest
public class FournisseurRepositoryTest {
    @Autowired
    private FournisseurRepository repository;

    @Test
    void shouldFindAllGoodSize() {
        List<Fournisseur> fournisseurs = this.repository.findAll();

        Assertions.assertEquals(4, fournisseurs.size());
    }
}

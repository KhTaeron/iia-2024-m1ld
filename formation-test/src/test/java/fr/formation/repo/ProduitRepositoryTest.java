package fr.formation.repo;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.formation.model.Produit;

@DataJpaTest
public class ProduitRepositoryTest {
	private static final String PRODUCT_NAME = "Produit Nom";

	@Autowired
	private ProduitRepository produitRepository;

	@Test
	void shouldFindByIdExists() {
		Optional<Produit> optProduit = this.produitRepository.findById("produitId");

		Assertions.assertTrue(optProduit.isPresent());
		Assertions.assertEquals(PRODUCT_NAME, optProduit.get().getName());
		Assertions.assertEquals(new BigDecimal("8526.00"), optProduit.get().getPrice());
	}

	@Test
	void shouldFindByIdDoesNotExist() {
		Optional<Produit> optProduit = this.produitRepository.findById("azerty");

		Assertions.assertFalse(optProduit.isPresent());
	}
}

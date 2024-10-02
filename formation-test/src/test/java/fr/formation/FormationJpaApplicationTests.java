package fr.formation;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;

// @SpringBootTest
@DataJpaTest
class FormationJpaApplicationTests {

	@Autowired
	private ProduitRepository produitRepository;

	@Test
	void shouldFindById() {
		Optional<Produit> optProduit = this.produitRepository.findById("produitId");
	}

	@Test
	void contextLoads() {
		Assertions.assertEquals(true, true);
		Assertions.assertEquals(10, 6 + 4);
		Assertions.assertTrue(true);
		Assertions.assertTrue((6 + 4 == 10));
		Assertions.assertFalse(false);
		Assertions.assertNull(null);
		Assertions.assertNotNull("null");

		Assertions.assertThrows(
			RuntimeException.class
			,
			() -> {
				throw new RuntimeException();
			}
		);
	}
}

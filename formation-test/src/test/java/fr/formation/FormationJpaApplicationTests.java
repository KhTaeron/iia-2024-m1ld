package fr.formation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// @SpringBootTest
class FormationJpaApplicationTests {
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

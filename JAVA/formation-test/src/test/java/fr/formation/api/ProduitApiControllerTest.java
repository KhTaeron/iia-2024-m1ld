package fr.formation.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ProduitApiControllerTest {
    // @Autowired
    // private ProduitRepository repository;

    // @Autowired
    // private ProduitApiController ctrl;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindAllStatusUnauthorized() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get("/api/produit")
            )
        
        // then
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
        ;

        // Mockito.verify(this.repository).findAll();
    }

    @Test
    @WithMockUser
    void shouldFindAllStatusOk() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get("/api/produit")
            )
        
        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        // Mockito.verify(this.repository).findAll();
    }
}

package fr.formation.api;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;
import fr.formation.response.ProduitResponse;

@ExtendWith(MockitoExtension.class)
public class ProduitApiControllerTest {
    @Mock
    private ProduitRepository repository;

    @InjectMocks
    private ProduitApiController ctrl;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(this.ctrl)
            .build()
        ;
    }

    @Test
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

        Mockito.verify(this.repository).findAll();
    }

    @Test
    void shouldFindAllReturnsProduitResponses() {
        // Given
        Produit p1 = new Produit();
        Produit p2 = new Produit();

        List<Produit> produits = List.of(p1, p2);

        Mockito
            .when(this.repository.findAll())
            .thenReturn(produits)
        ;

        // When
        List<ProduitResponse> result = this.ctrl.findAll();

        // Then
        Assertions.assertEquals(produits.size(), result.size());

        Mockito
            .verify(this.repository, Mockito.times(1))
            .findAll()
        ;
    }
}

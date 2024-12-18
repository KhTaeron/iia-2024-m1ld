package fr.formation.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.repo.FournisseurRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class FournisseurApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FournisseurRepository repository;

    @Test
    void shouldFindAllStatusUnauthorizedWhenNotLogged() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/fournisseur"))
        
        // then
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
        ;

        Mockito.verify(this.repository, Mockito.never()).findAll();
    }

    @Test
    @WithMockUser
    void shouldFindAllStatusForbiddenWhenNotAdmin() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/fournisseur"))
        
        // then
            .andExpect(MockMvcResultMatchers.status().isForbidden())
        ;
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldFindAllStatusOkWhenAdmin() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/fournisseur"))
        
        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(this.repository).findAll();
    }
}

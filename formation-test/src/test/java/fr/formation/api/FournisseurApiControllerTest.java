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
public class FournisseurApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindAllStatusUnauthorizedWhenNotLogged() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/fournisseur"))
        
        // then
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
        ;
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
    }
}

package fr.formation.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.TestUtil;
import fr.formation.annotation.WithMockUserId;
import fr.formation.request.CreateCommentRequest;

@SpringBootTest
@AutoConfigureMockMvc
class CommentApiControllerIntegrationTest {
    private static final String VIDEO_ID = "video1";
    private static final String COMMENT_ID = "com1";
    
    private static final String ENDPOINT = "/api/comment/" + VIDEO_ID;
    private static final String ENDPOINT_BY_ID = ENDPOINT + "/" + COMMENT_ID;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindAllByVideoIdStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT));

        // then
        result
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("com1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("Le contenu 1"))
        ;
    }

    @Test
    void shouldCreateStatusForbidden() throws Exception {
        // given
        CreateCommentRequest request = CreateCommentRequest.builder()
            .content("Contenu du commentaire")
            .build()
        ;

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUserId
    void shouldCreateStatusCreated() throws Exception {
        // given
        CreateCommentRequest request = CreateCommentRequest.builder()
            .content("Contenu du commentaire")
            .build()
        ;

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldDeleteByIdStatusForbiddenWhenNotLogged() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser
    void shouldDeleteByIdStatusForbiddenWhenNotAdmin() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteByIdStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

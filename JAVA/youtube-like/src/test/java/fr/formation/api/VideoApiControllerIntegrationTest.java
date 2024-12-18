package fr.formation.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.TestUtil;
import fr.formation.annotation.WithMockUserId;
import fr.formation.request.CreateOrUpdateVideoRequest;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:/reset-video.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class VideoApiControllerIntegrationTest {
    private static final String VIDEO_ID = "video1";
    
    private static final String ENDPOINT = "/api/video";
    private static final String ENDPOINT_BY_ID = ENDPOINT + "/" + VIDEO_ID;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindAllStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT));

        // then
        result
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("video1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Video 1"))
        ;
    }

    @Test
    void shouldCreateStatusForbidden() throws Exception {
        // given
        CreateOrUpdateVideoRequest request = CreateOrUpdateVideoRequest.builder()
            .name("Nom de la vidéo")
            .description("Description de la vidéo")
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
        CreateOrUpdateVideoRequest request = CreateOrUpdateVideoRequest.builder()
            .name("Nom de la vidéo")
            .description("Description de la vidéo")
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
    void shouldUpdateStatusForbidden() throws Exception {
        // given
        CreateOrUpdateVideoRequest request = CreateOrUpdateVideoRequest.builder()
            .name("Nom de la vidéo")
            .description("Description de la vidéo")
            .build()
        ;

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .put(ENDPOINT_BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUserId
    void shouldUpdateStatusCreated() throws Exception {
        // given
        CreateOrUpdateVideoRequest request = CreateOrUpdateVideoRequest.builder()
            .name("Nom de la vidéo")
            .description("Description de la vidéo")
            .build()
        ;

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .put(ENDPOINT_BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    void shouldDeleteByIdStatusForbidden() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUserId
    void shouldDeleteByIdStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

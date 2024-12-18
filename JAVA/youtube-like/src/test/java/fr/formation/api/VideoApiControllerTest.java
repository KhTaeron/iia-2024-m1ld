package fr.formation.api;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.formation.TestUtil;
import fr.formation.model.User;
import fr.formation.model.Video;
import fr.formation.repo.UserRepository;
import fr.formation.repo.VideoRepository;
import fr.formation.request.CreateOrUpdateVideoRequest;

@ExtendWith(MockitoExtension.class)
class VideoApiControllerTest {
    private static final String VIDEO_ID = "video-id-863";
    private static final String USER_ID = "user-id-117";
    
    private static final String ENDPOINT = "/api/video";
    private static final String ENDPOINT_BY_ID = ENDPOINT + "/" + VIDEO_ID;

    @Mock
    private VideoRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;
    
    @InjectMocks
    private VideoApiController ctrl;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.ctrl).build();
    }

    @Test
    void shouldFindAllStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.repository).findAll();
    }

    @Test
    void shouldFindByIdStatusOk() throws Exception {
        // given
        Mockito.when(this.repository.findById(VIDEO_ID)).thenReturn(Optional.of(new Video()));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.repository).findById(VIDEO_ID);
    }

    @Test
    void shouldCreateStatusCreated() throws Exception {
        // given
        Mockito.when(this.userRepository.findById(USER_ID)).thenReturn(Optional.of(new User()));

        CreateOrUpdateVideoRequest request = CreateOrUpdateVideoRequest.builder()
            .name("Nom de la vidéo")
            .description("Description de la vidéo")
            .build()
        ;

        Mockito.when(this.authentication.getPrincipal()).thenReturn(USER_ID);

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
                .principal(authentication)
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(this.userRepository).findById(USER_ID);
        Mockito.verify(this.repository).save(Mockito.any());
    }

    @ParameterizedTest
    @MethodSource("provideBadCreateOrUpdateRequests")
    void shouldCreateStatusBadRequest(CreateOrUpdateVideoRequest request) throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
                .principal(authentication)
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.userRepository, Mockito.never()).findById(Mockito.any());
        Mockito.verify(this.repository, Mockito.never()).save(Mockito.any());
    }
 
    @Test
    void shouldUpdateStatusOk() throws Exception {
        // given
        Mockito.when(this.repository.findById(VIDEO_ID)).thenReturn(Optional.of(new Video()));

        CreateOrUpdateVideoRequest request = CreateOrUpdateVideoRequest.builder()
            .name("Nouveau nom")
            .build()
        ;

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .put(ENDPOINT_BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
                .principal(authentication)
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.userRepository, Mockito.never()).findById(USER_ID);
        Mockito.verify(this.repository).findById(VIDEO_ID);
        Mockito.verify(this.repository).save(Mockito.any());
    }

    @ParameterizedTest
    @MethodSource("provideBadCreateOrUpdateRequests")
    void shouldUpdateStatusBadRequest(CreateOrUpdateVideoRequest request) throws Exception {
        // given
        
        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .put(ENDPOINT_BY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
                .principal(authentication)
            )
        ;

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.userRepository, Mockito.never()).findById(Mockito.any());
        Mockito.verify(this.repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldDeleteByIdStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.repository).deleteById(VIDEO_ID);
    }

    public static Stream<Arguments> provideBadCreateOrUpdateRequests() {
        return Stream.of(
            Arguments.of(
                CreateOrUpdateVideoRequest.builder().build()
            ),

            Arguments.of(
                CreateOrUpdateVideoRequest.builder().name(" ").build()
            ),

            Arguments.of(
                CreateOrUpdateVideoRequest.builder().name("").build()
            )
        );
    }
}

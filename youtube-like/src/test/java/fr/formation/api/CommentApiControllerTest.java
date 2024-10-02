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
import fr.formation.repo.CommentRepository;
import fr.formation.repo.UserRepository;
import fr.formation.repo.VideoRepository;
import fr.formation.request.CreateCommentRequest;

@ExtendWith(MockitoExtension.class)
class CommentApiControllerTest {
    private static final String VIDEO_ID = "video-id-863";
    private static final String COMMENT_ID = "comment-id-118";
    private static final String USER_ID = "user-id-007";
    
    private static final String ENDPOINT = "/api/comment/" + VIDEO_ID;
    private static final String ENDPOINT_BY_ID = ENDPOINT + "/" + COMMENT_ID;

    @Mock
    private CommentRepository repository;
    
    @Mock
    private VideoRepository videoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;
    
    @InjectMocks
    private CommentApiController ctrl;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.ctrl).build();
    }

    @Test
    void shouldFindAllByVideoIdStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.repository).findAllByVideoId(VIDEO_ID);
    }

    @Test
    void shouldCreateStatusCreated() throws Exception {
        // given
        Mockito.when(this.userRepository.findById(USER_ID)).thenReturn(Optional.of(new User()));
        Mockito.when(this.videoRepository.findById(VIDEO_ID)).thenReturn(Optional.of(new Video()));

        CreateCommentRequest request = CreateCommentRequest.builder()
            .content("Contenu du commentaire")
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
        Mockito.verify(this.videoRepository).findById(VIDEO_ID);
        Mockito.verify(this.repository).save(Mockito.any());
    }

    @ParameterizedTest
    @MethodSource("provideBadCreateRequests")
    void shouldCreateStatusBadRequest(CreateCommentRequest request) throws Exception {
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
        Mockito.verify(this.videoRepository, Mockito.never()).findById(Mockito.any());
        Mockito.verify(this.repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldDeleteByIdStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.repository).deleteById(COMMENT_ID);
    }

    public static Stream<Arguments> provideBadCreateRequests() {
        return Stream.of(
            Arguments.of(
                CreateCommentRequest.builder().build()
            ),

            Arguments.of(
                CreateCommentRequest.builder().content(" ").build()
            ),

            Arguments.of(
                CreateCommentRequest.builder().content("").build()
            )
        );
    }
}

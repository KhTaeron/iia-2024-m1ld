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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.formation.TestUtil;
import fr.formation.model.User;
import fr.formation.repo.UserRepository;
import fr.formation.request.AuthRequest;
import fr.formation.request.SubscribeRequest;

@ExtendWith(MockitoExtension.class)
class UserApiControllerTest {
    private static final String ENDPOINT = "/api/user";
    private static final String ENDPOINT_AUTH = ENDPOINT + "/auth";
    private static final String ENDPOINT_SUBSCRIBE = ENDPOINT + "/subscribe";

    private static final String USER_NAME = "The God";
    private static final String USER_USERNAME = "god";
    private static final String USER_PASSWORD = "123456$";

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserApiController ctrl;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.ctrl).build();
    }

    @Test
    void shouldAuthStatusOk() throws Exception {
        // given
        AuthRequest request = AuthRequest.builder()
            .username(USER_USERNAME)
            .password(USER_PASSWORD)
            .build()
        ;

        Mockito.when(this.repository.findByUsername(USER_USERNAME)).thenReturn(Optional.of(new User()));

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT_AUTH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.repository).findByUsername(USER_USERNAME);
    }

    @Test
    void shouldAuthStatusOkEvenIfBadCredentialsThrown() throws Exception {
        // given
        AuthRequest request = AuthRequest.builder()
            .username(USER_USERNAME)
            .password(USER_PASSWORD)
            .build()
        ;

        Mockito.when(this.authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT_AUTH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @ParameterizedTest
    @MethodSource("provideBadAuthRequests")
    void shouldAuthStatusBadRequest(AuthRequest request) throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT_AUTH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldSubscribeStatusCreated() throws Exception {
        // given
        SubscribeRequest request = SubscribeRequest.builder()
            .name("Nom d'utilisateur")
            .username("nomutilisateur")
            .password("123456$")
            .build()
        ;

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT_SUBSCRIBE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(this.passwordEncoder).encode(USER_PASSWORD);
        Mockito.verify(this.repository).save(Mockito.any());
    }

    @ParameterizedTest
    @MethodSource("provideBadSubscribeRequests")
    void shouldCreateStatusBadRequest(SubscribeRequest request) throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT_SUBSCRIBE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.json(request))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.passwordEncoder, Mockito.never()).encode(Mockito.any());
        Mockito.verify(this.repository, Mockito.never()).save(Mockito.any());
    }
 
    public static Stream<Arguments> provideBadAuthRequests() {
        return Stream.of(
            Arguments.of(
                AuthRequest.builder().build()
            ),

            Arguments.of(
                AuthRequest.builder()
                    .username(" ")
                    .password(USER_PASSWORD)
                    .build()
            ),

            Arguments.of(
                AuthRequest.builder()
                    .username(USER_USERNAME)
                    .password("")
                    .build()
            ),

            Arguments.of(
                AuthRequest.builder()
                    .username(USER_USERNAME)
                    .build()
            )
        );
    }
 
    public static Stream<Arguments> provideBadSubscribeRequests() {
        return Stream.of(
            Arguments.of(
                SubscribeRequest.builder().build()
            ),

            Arguments.of(
                SubscribeRequest.builder()
                    .name(USER_NAME)
                    .username(" ")
                    .password(USER_PASSWORD)
                    .build()
            ),

            Arguments.of(
                SubscribeRequest.builder()
                    .name(USER_NAME)
                    .username(USER_USERNAME)
                    .password("")
                    .build()
            ),

            Arguments.of(
                SubscribeRequest.builder()
                    .username(USER_USERNAME)
                    .password(USER_PASSWORD)
                    .build()
            )
        );
    }
}

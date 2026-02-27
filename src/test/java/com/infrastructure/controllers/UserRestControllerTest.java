package com.infrastructure.controllers;

import com.entity.UserImp;
import com.usecase.admin.CreateUserUseCase;
import com.usecase.admin.ExternalUserProvider;
import com.usecase.admin.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TEST D'ENTRÉE (WEB)
 * On vérifie que le contrôleur REST expose bien l'API et délègue au Use Case.
 */
@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    // Ces mocks sont nécessaires car App.java contient des définitions de beans
    // qui dépendent de ces interfaces, même si elles ne sont pas utilisées par ce
    // contrôleur.
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ExternalUserProvider externalUserProvider;

    @Test
    void should_return_200_and_json_when_user_created() throws Exception {
        // GIVEN
        String jsonRequest = "{\"id\":\"U10\", \"label\":\"Jean Test\"}";

        // On simule le comportement du Use Case
        Mockito.when(createUserUseCase.execute("U10", "Jean Test"))
                .thenReturn(new UserImp("U10", "Jean Test"));

        // WHEN & THEN
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("U10"))
                .andExpect(jsonPath("$.label").value("Jean Test"))
                .andExpect(jsonPath("$.message").exists());
    }
}

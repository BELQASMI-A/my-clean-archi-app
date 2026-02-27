package com.usecase.admin;

import com.entity.User;
import com.entity.UserImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * TEST UNITAIRE (DOMAIN)
 * Ici, on teste uniquement la logique métier du Use Case.
 * Aucune dépendance à Spring, Hibernate ou une DB.
 */
class CreateUserUseCaseTest {

    private UserRepository repositoryMock;
    private CreateUserUseCase useCase;

    @BeforeEach
    void setUp() {
        // On crée un simulacre (Mock) du port
        repositoryMock = Mockito.mock(UserRepository.class);
        // On injecte le mock dans le use case
        useCase = new CreateUserUseCase(repositoryMock);
    }

    @Test
    void should_create_user_successfully() {
        // GIVEN
        String id = "U1";
        String label = "Amine BEL";
        when(repositoryMock.findById(id)).thenReturn(null); // L'utilisateur n'existe pas encore

        // WHEN
        User result = useCase.execute(id, label);

        // THEN
        assertNotNull(result);
        assertEquals("Amine BEL", result.getLabel());

        // On vérifie que le repository a bien été appelé pour sauvegarder l'objet
        verify(repositoryMock, times(1)).save(any(User.class));
    }

    @Test
    void should_fail_if_name_is_too_short() {
        // GIVEN
        String id = "U2";
        String label = "Am"; // Trop court (min 3)

        // WHEN & THEN
        Exception exception = assertThrows(RuntimeException.class, () -> {
            useCase.execute(id, label);
        });

        assertTrue(exception.getMessage().contains("au moins 3 caractères"));
        // On vérifie que save() n'a JAMAIS été appelé
        verify(repositoryMock, never()).save(any());
    }

    @Test
    void should_fail_if_id_already_exists() {
        // GIVEN
        String id = "EXISTING";
        String label = "New User";
        // On simule que findById renvoie déjà quelqu'un
        when(repositoryMock.findById(id)).thenReturn(new UserImp(id, "Other User"));

        // WHEN & THEN
        Exception exception = assertThrows(RuntimeException.class, () -> {
            useCase.execute(id, label);
        });

        assertTrue(exception.getMessage().contains("déjà utilisé"));
        verify(repositoryMock, never()).save(any());
    }
}

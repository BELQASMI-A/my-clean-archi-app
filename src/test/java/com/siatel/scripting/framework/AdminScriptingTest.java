package com.siatel.scripting.framework;

import com.entity.UserImp;
import com.usecase.admin.CreateUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * TEST D'ENTRÉE (SCRIPTING / RHINO ADAPTER)
 * On vérifie que le pont AdminScripting appelle bien le Use Case.
 */
class AdminScriptingTest {

    private CreateUserUseCase useCaseMock;
    private StringBuilder logs;
    private AdminScripting adminScripting;

    @BeforeEach
    void setUp() {
        useCaseMock = mock(CreateUserUseCase.class);
        logs = new StringBuilder();
        adminScripting = new AdminScripting(useCaseMock, logs);
    }

    @Test
    void should_delegate_to_usecase_when_createUser_called() {
        // GIVEN
        UserImp user = new UserImp("RS1", "Rhino User");

        // WHEN
        adminScripting.createUser(user);

        // THEN
        verify(useCaseMock, times(1)).execute("RS1", "Rhino User");
        assertTrue(logs.toString().contains("[SUCCESS]"));
    }

    @Test
    void should_log_error_when_usecase_fails() {
        // GIVEN
        UserImp user = new UserImp("ERR", "E");
        doThrow(new RuntimeException("Nom trop court")).when(useCaseMock).execute(anyString(), anyString());

        // WHEN
        adminScripting.createUser(user);

        // THEN
        assertTrue(logs.toString().contains("[ERROR]"));
        assertTrue(logs.toString().contains("Nom trop court"));
    }
}

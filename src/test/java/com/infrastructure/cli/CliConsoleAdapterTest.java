package com.infrastructure.cli;

import com.usecase.admin.CreateUserUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class CliConsoleAdapterTest {

    @Test
    void should_call_create_user_usecase_when_args_are_correct() throws Exception {
        // GIVEN
        CreateUserUseCase useCaseMock = mock(CreateUserUseCase.class);
        CliConsoleAdapter cliAdapter = new CliConsoleAdapter(useCaseMock);
        String[] args = { "--create-user", "CLI_01", "Amine Test CLI" };

        // WHEN
        cliAdapter.run(args);

        // THEN
        verify(useCaseMock, times(1)).execute("CLI_01", "Amine Test CLI");
    }

    @Test
    void should_do_nothing_when_no_args_provided() throws Exception {
        // GIVEN
        CreateUserUseCase useCaseMock = mock(CreateUserUseCase.class);
        CliConsoleAdapter cliAdapter = new CliConsoleAdapter(useCaseMock);
        String[] args = {};

        // WHEN
        cliAdapter.run(args);

        // THEN
        verify(useCaseMock, never()).execute(anyString(), anyString());
    }

    @Test
    void should_ignore_unknown_commands() throws Exception {
        // GIVEN
        CreateUserUseCase useCaseMock = mock(CreateUserUseCase.class);
        CliConsoleAdapter cliAdapter = new CliConsoleAdapter(useCaseMock);
        String[] args = { "--unknown-command", "something" };

        // WHEN
        cliAdapter.run(args);

        // THEN
        verify(useCaseMock, never()).execute(anyString(), anyString());
    }
}

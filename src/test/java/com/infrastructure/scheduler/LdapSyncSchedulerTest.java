package com.infrastructure.scheduler;

import com.usecase.admin.LdapConfig;
import com.usecase.admin.SyncUsersFromLdapUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LdapSyncSchedulerTest {

    @Test
    void should_call_sync_usecase_when_scheduler_runs() {
        // GIVEN
        SyncUsersFromLdapUseCase syncUseCaseMock = Mockito.mock(SyncUsersFromLdapUseCase.class);
        LdapSyncScheduler scheduler = new LdapSyncScheduler(syncUseCaseMock);

        // WHEN
        scheduler.scheduleLdapSync();

        // THEN
        // On vérifie que le Use Case a été appelé une fois
        verify(syncUseCaseMock, times(1)).execute(any(LdapConfig.class));
    }
}

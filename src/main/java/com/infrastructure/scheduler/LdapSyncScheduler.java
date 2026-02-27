package com.infrastructure.scheduler;

import com.usecase.admin.LdapConfig;
import com.usecase.admin.SyncUsersFromLdapUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ADAPTER : Scheduler (Tâches planifiées)
 * Une autre porte d'entrée vers le domaine, sans passer par le Web.
 */
@Component
public class LdapSyncScheduler {

    private static final Logger log = LoggerFactory.getLogger(LdapSyncScheduler.class);
    private final SyncUsersFromLdapUseCase syncUseCase;

    public LdapSyncScheduler(SyncUsersFromLdapUseCase syncUseCase) {
        this.syncUseCase = syncUseCase;
    }

    /**
     * S'exécute toutes les nuits à minuit (Cron: 0 0 0 * * *)
     * Pour le test, on met toutes les 5 minutes (300000 ms)
     */
    @Scheduled(fixedRate = 300000)
    public void scheduleLdapSync() {
        log.info("SCHEDULER : Lancement automatique du job de synchronisation LDAP...");

        try {
            // Dans un cas réel, on lirait cette config depuis un fichier
            // application.properties
            LdapConfig defaultCronConfig = new LdapConfig(
                    "ldap://urd.ucp",
                    389,
                    "dc=u-cergy,dc=fr",
                    "uid=gargantua-prod,ou=system,dc=u-cergy,dc=fr",
                    "sJG<N-6]$v#WzFcgg\"",
                    "(accountStatus=actif)");

            syncUseCase.execute(defaultCronConfig);
            log.info("SCHEDULER : Synchronisation terminée avec succès.");

        } catch (Exception e) {
            log.error("SCHEDULER : Échec de la synchronisation automatique : {}", e.getMessage());
        }
    }
}

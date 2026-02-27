package com.usecase.admin;

import com.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Use Case pour synchroniser les utilisateurs LDAP vers le système local
 * (PostgreSQL)
 */
public class SyncUsersFromLdapUseCase {

    private static final Logger log = LoggerFactory.getLogger(SyncUsersFromLdapUseCase.class);

    private final ExternalUserProvider ldapProvider;
    private final CreateUserUseCase createUserUseCase;

    public SyncUsersFromLdapUseCase(ExternalUserProvider ldapProvider, CreateUserUseCase createUserUseCase) {
        this.ldapProvider = ldapProvider;
        this.createUserUseCase = createUserUseCase;
    }

    public int execute(LdapConfig config) {
        log.info("SYNC USE CASE : Démarrage de la synchronisation...");

        // 1. Récupération depuis LDAP
        List<User> externalUsers = ldapProvider.fetchUsers(config);

        // 2. Nettoyage de la base locale pour assurer une synchro propre
        log.info("SYNC USE CASE : Nettoyage de la table locale...");
        createUserUseCase.getUserRepository().deleteAll();

        int syncCount = 0;

        // 3. Intégration dans le système local
        for (User extUser : externalUsers) {
            try {
                // On utilise le Use Case existant pour bénéficier des validations métier
                createUserUseCase.execute(extUser.getId(), extUser.getLabel());
                syncCount++;
            } catch (Exception e) {
                // Si l'utilisateur existe déjà ou erreur de validation, on logue et on continue
                log.warn("SYNC USE CASE : Saut de l'utilisateur '{}' : {}", extUser.getLabel(), e.getMessage());
            }
        }

        log.info("SYNC USE CASE : Synchronisation terminée. {} utilisateurs importés/mis à jour.", syncCount);
        return syncCount;
    }
}

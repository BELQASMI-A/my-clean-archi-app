package com.infrastructure.controllers;

import com.usecase.admin.ExternalUserProvider;
import com.usecase.admin.LdapConfig;
import com.usecase.admin.SyncUsersFromLdapUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ldap")
public class LdapRestController {

    private final ExternalUserProvider ldapProvider;
    private final SyncUsersFromLdapUseCase syncUseCase;

    public LdapRestController(ExternalUserProvider ldapProvider, SyncUsersFromLdapUseCase syncUseCase) {
        this.ldapProvider = ldapProvider;
        this.syncUseCase = syncUseCase;
    }

    @PostMapping("/test")
    public Map<String, Object> testLdap(@RequestBody LdapConfig config) {
        boolean success = ldapProvider.testConnection(config);

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Connexion LDAP réussie !" : "Échec de la connexion à l'annuaire.");
        return response;
    }

    @PostMapping("/sync")
    public Map<String, Object> syncLdap(@RequestBody LdapConfig config) {
        try {
            int count = syncUseCase.execute(config);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("count", count);
            response.put("message", "Synchronisation terminée : " + count + " utilisateurs synchronisés.");
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erreur lors de la synchronisation : " + e.getMessage());
            return response;
        }
    }
}

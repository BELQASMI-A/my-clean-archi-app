package com.usecase.admin;

/**
 * Objet de domaine représentant la configuration LDAP
 */
public record LdapConfig(
                String url,
                int port,
                String baseDn,
                String managerDn,
                String password,
                String userFilter) {
}

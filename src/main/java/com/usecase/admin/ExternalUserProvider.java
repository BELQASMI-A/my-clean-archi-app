package com.usecase.admin;

import com.entity.User;
import java.util.List;

/**
 * Port de sortie (Interface) pour communiquer avec un annuaire externe.
 */
public interface ExternalUserProvider {
    /**
     * Tente une connexion avec la configuration fournie
     * 
     * @return true si la connexion réussit, false sinon
     */
    boolean testConnection(LdapConfig config);

    /**
     * Récupère la liste des utilisateurs depuis l'annuaire externe
     * 
     * @param config Configuration LDAP
     * @return Liste d'utilisateurs (objets domaine User)
     */
    List<User> fetchUsers(LdapConfig config);
}

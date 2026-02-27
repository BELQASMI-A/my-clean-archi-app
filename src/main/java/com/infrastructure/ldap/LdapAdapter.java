package com.infrastructure.ldap;

import com.usecase.admin.ExternalUserProvider;
import com.usecase.admin.LdapConfig;
import com.entity.User;
import com.entity.UserImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Component
public class LdapAdapter implements ExternalUserProvider {

    private static final Logger log = LoggerFactory.getLogger(LdapAdapter.class);

    @Override
    public boolean testConnection(LdapConfig config) {
        log.info("LDAP ADAPTER : Test de connexion à {}", config.url());
        Hashtable<String, String> env = getJndiEnv(config);
        try {
            DirContext ctx = new InitialDirContext(env);
            ctx.close();
            log.info("LDAP ADAPTER [SUCCESS] : Connexion réussie.");
            return true;
        } catch (Exception e) {
            log.error("LDAP ADAPTER [ERROR] : Échec de la connexion : {}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<User> fetchUsers(LdapConfig config) {
        log.info("LDAP ADAPTER : Récupération des utilisateurs (Base: {})", config.baseDn());
        List<User> users = new ArrayList<>();
        Hashtable<String, String> env = getJndiEnv(config);

        try {
            DirContext ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // On utilise le filtre configuré, sinon un filtre par défaut
            String filter = config.userFilter();
            if (filter == null || filter.trim().isEmpty()) {
                filter = "(objectClass=person)";
            }
            log.info("LDAP ADAPTER : Utilisation du filtre -> {}", filter);

            NamingEnumeration<SearchResult> results = ctx.search(config.baseDn(), filter, controls);

            while (results.hasMore()) {
                SearchResult entry = results.next();
                Attributes attrs = entry.getAttributes();

                Attribute uidAttr = attrs.get("uid");
                if (uidAttr == null)
                    uidAttr = attrs.get("cn");

                Attribute cnAttr = attrs.get("cn");
                if (cnAttr == null)
                    cnAttr = attrs.get("sn");

                if (uidAttr != null && cnAttr != null) {
                    String id = (String) uidAttr.get();
                    String label = (String) cnAttr.get();
                    users.add(new UserImp(id, label));
                }
            }
            ctx.close();
            log.info("LDAP ADAPTER : {} utilisateurs trouvés.", users.size());
        } catch (Exception e) {
            log.error("LDAP ADAPTER : Erreur lors du fetch : {}", e.getMessage());
        }
        return users;
    }

    private Hashtable<String, String> getJndiEnv(LdapConfig config) {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

        String ldapUrl = config.url();
        if (!ldapUrl.startsWith("ldap://") && !ldapUrl.startsWith("ldaps://")) {
            ldapUrl = "ldap://" + ldapUrl;
        }
        int startOfHost = ldapUrl.indexOf("//") + 2;
        if (ldapUrl.indexOf(":", startOfHost) == -1) {
            ldapUrl = ldapUrl + ":" + config.port();
        }

        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, config.managerDn());
        env.put(Context.SECURITY_CREDENTIALS, config.password());
        env.put(Context.REFERRAL, "follow");
        env.put("com.sun.jndi.ldap.connect.timeout", "3000");
        return env;
    }
}

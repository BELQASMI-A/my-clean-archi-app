package com.infrastructure.cli;

import com.usecase.admin.CreateUserUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * ADAPTER : CLI (Command Line Interface)
 * Permet aux utilisateurs techniques d'interagir avec le domaine via console.
 */
@Component
public class CliConsoleAdapter implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CliConsoleAdapter.class);
    private final CreateUserUseCase createUserUseCase;

    public CliConsoleAdapter(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0)
            return;

        log.info("CLI ADAPTER : Analyse des arguments de commande : {}", Arrays.toString(args));

        // Exemple: --create-user ID LABEL
        if (args.length >= 3 && "--create-user".equals(args[0])) {
            String id = args[1];
            String label = args[2];

            try {
                createUserUseCase.execute(id, label);
                log.info("CLI : [SUCCESS] Utilisateur '{}' créé via console.", label);
            } catch (Exception e) {
                log.error("CLI : [ERROR] Impossible de créer l'utilisateur : {}", e.getMessage());
            }
        }

        // On pourrait ajouter --sync-ldap, --delete-all, etc.
    }
}

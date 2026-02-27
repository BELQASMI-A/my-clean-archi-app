package com.usecase.admin;

import com.entity.User;
import com.entity.UserImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateUserUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateUserUseCase.class);
    private final UserRepository userRepository;

    // On dépend de l'interface (Port), pas de la base de données
    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User execute(String id, String label) {
        // [RÈGLE MÉTIER] : Validation simple
        if (label == null || label.trim().length() < 3) {
            throw new RuntimeException("Erreur Métier : Le nom de l'utilisateur doit avoir au moins 3 caractères.");
        }

        // [LOGIQUE] : Si l'utilisateur existe déjà, on pourrait lever une erreur ici
        if (userRepository.findById(id) != null) {
            throw new RuntimeException("Erreur Métier : L'ID utilisateur '" + id + "' est déjà utilisé.");
        }

        // [ACTION] : Création de l'entité
        User newUser = new UserImp(id, label);

        // [PERSISTANCE] : On passe par le Port
        userRepository.save(newUser);

        log.info("USE CASE : Utilisateur '{}' validé et sauvegardé via le repository.", label);
        return newUser;
    }
}

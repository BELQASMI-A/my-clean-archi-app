package com.infrastructure.controllers;

import com.entity.User;
import com.usecase.admin.CreateUserUseCase;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final CreateUserUseCase createUserUseCase;

    // L'injection se fait par le constructeur
    public UserRestController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        User user = createUserUseCase.execute(request.getId(), request.getLabel());
        return new UserResponse(user.getId(), user.getLabel(), "Utilisateur créé avec succès !");
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        // En Clean Archi, on passerait par un Use Case "ListUsers"
        // Ici on accède au repo via le UseCase pour la démonstration
        return createUserUseCase.getUserRepository().findAll();
    }

    // DTOs internes pour le Web (ne polluent pas le domaine)
    public static class UserRequest {
        private String id;
        private String label;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class UserResponse {
        private String id;
        private String label;
        private String message;

        public UserResponse(String id, String label, String message) {
            this.id = id;
            this.label = label;
            this.message = message;
        }

        public String getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }

        public String getMessage() {
            return message;
        }
    }
}

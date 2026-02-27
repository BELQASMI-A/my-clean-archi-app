package com.infrastructure.controllers;

import com.siatel.scripting.framework.AdminScripting;
import com.usecase.admin.CreateUserUseCase;
import com.usecase.admin.GroupRepository;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/script")
public class ScriptRestController {

    private final CreateUserUseCase createUserUseCase;

    public ScriptRestController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/execute")
    public Map<String, String> executeScript(@RequestBody Map<String, String> payload) {
        String code = payload.get("code");
        StringBuilder logs = new StringBuilder();

        // Initialisation de l'environnement Rhino
        Context ctx = Context.enter();
        try {
            Scriptable scope = ctx.initStandardObjects();

            // Création de l'adaptateur Admin
            AdminScripting admin = new AdminScripting(createUserUseCase, logs);

            // Exposition de l'objet 'Admin' au script JS
            Object wrappedAdmin = Context.javaToJS(admin, scope);
            ScriptableObject.putProperty(scope, "Admin", wrappedAdmin);

            // Ajout du support pour console.log()
            Object consoleObj = Context.javaToJS(new ScriptConsole(logs), scope);
            ScriptableObject.putProperty(scope, "console", consoleObj);

            // Exposition d'une fonction simple log() pour le debug
            logs.append("Démarrage du script...\n");
            ctx.evaluateString(scope, code, "GargantuaConsole", 1, null);
            logs.append("Script terminé.\n");

        } catch (Exception e) {
            logs.append("[EXCEPTION] ").append(e.getMessage()).append("\n");
        } finally {
            Context.exit();
        }

        Map<String, String> response = new HashMap<>();
        response.put("logs", logs.toString());
        return response;
    }

    /**
     * Classe helper pour le pont entre JS (console.log) et Java
     */
    public static class ScriptConsole {
        private final StringBuilder logs;

        public ScriptConsole(StringBuilder logs) {
            this.logs = logs;
        }

        public void log(Object msg) {
            logs.append("[CONSOLE] ").append(msg).append("\n");
        }
    }
}

package com.siatel.scripting.framework;

import com.entity.User;
import com.entity.UserImp;
import com.usecase.admin.CreateUserUseCase;
import com.usecase.admin.GroupRepository;
import org.mozilla.javascript.NativeJavaObject;

/**
 * Adapter de Scripting (Couche Verte / Interface Adapter)
 * Cet objet est exposé sous le nom 'Admin' dans les scripts JS.
 */
public class AdminScripting {

    private final CreateUserUseCase createUserUseCase;
    private final StringBuilder scriptLogs;

    public AdminScripting(CreateUserUseCase createUserUseCase, StringBuilder logs) {
        this.createUserUseCase = createUserUseCase;
        this.scriptLogs = logs;
    }

    /**
     * JS: Admin.newUser('id', 'nom')
     * Permet de créer un objet Java proprement depuis JS
     */
    public User newUser(String id, String label) {
        return new UserImp(id, label);
    }

    /**
     * JS: Admin.createUser(userObject)
     * Reçoit un objet enveloppé par Rhino (NativeJavaObject)
     */
    public final void createUser(Object object) {
        try {
            User user;
            if (object instanceof NativeJavaObject) {
                // On "déballe" l'objet réel pour le transformer en interface Java
                user = (User) ((NativeJavaObject) object).unwrap();
            } else {
                user = (User) object;
            }

            // On appelle le Use Case métier (Cœur)
            createUserUseCase.execute(user.getId(), user.getLabel());
            scriptLogs.append("[SUCCESS] Utilisateur créé : ").append(user.getLabel()).append("\n");

        } catch (Exception e) {
            scriptLogs.append("[ERROR] ").append(e.getMessage()).append("\n");
        }
    }
}

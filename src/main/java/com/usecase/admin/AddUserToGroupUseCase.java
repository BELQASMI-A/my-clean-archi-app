package com.usecase.admin;

import com.entity.User;
import com.entity.Group;

public class AddUserToGroupUseCase {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public AddUserToGroupUseCase(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public void execute(String userId, String groupId) {
        User user = userRepository.findById(userId);
        Group group = groupRepository.findById(groupId);

        if (user != null && group != null) {
            group.addUser(user);
            groupRepository.save(group);
            System.out.println("LOGIQUE : Utilisateur " + user.getLabel() + " ajouté au groupe " + group.getLabel());
        } else {
            throw new RuntimeException("Utilisateur ou Groupe introuvable");
        }
    }
}

package com.usecase.admin;

import com.entity.Group;
import com.entity.GroupImp;
import com.entity.User;

public class CreateGroupUseCase {

    private final GroupRepository groupRepository;

    public CreateGroupUseCase(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group execute(String groupId, String label, String description, String ownerId, long privileges, int dbId) {
        Group group = new GroupImp(groupId, label);
        group.setDescription(description);
        group.setOwnerId(ownerId);
        group.setPrivileges(privileges);
        group.setDbId(dbId);

        groupRepository.save(group);
        System.out.println(
                "LOGIQUE : Groupe '" + label + "' créé/mis à jour avec privilèges " + privileges + " sur base " + dbId);

        return group;
    }
}

package com.usecase.admin;

import com.entity.Group;

public interface GroupRepository {
    Group findById(String id);

    void save(Group group);
}

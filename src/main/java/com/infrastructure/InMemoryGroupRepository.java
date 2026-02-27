package com.infrastructure;

import com.entity.Group;
import com.entity.GroupImp;
import com.usecase.admin.GroupRepository;
import java.util.HashMap;
import java.util.Map;

public class InMemoryGroupRepository implements GroupRepository {
    private Map<String, Group> db = new HashMap<>();

    public InMemoryGroupRepository() {
        // Simulation de données
        db.put("G1", new GroupImp("G1", "Equipe Technique"));
    }

    @Override
    public Group findById(String id) {
        return db.get(id);
    }

    @Override
    public void save(Group group) {
        db.put(group.getId(), group);
    }
}

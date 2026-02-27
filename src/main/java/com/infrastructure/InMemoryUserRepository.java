package com.infrastructure;

import com.entity.User;
import com.entity.UserImp;
import com.usecase.admin.UserRepository;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> db = new HashMap<>();

    public InMemoryUserRepository() {
        // Simulation de données
        db.put("U1", new UserImp("U1", "Amine Admin"));
    }

    @Override
    public User findById(String id) {
        return db.get(id);
    }

    @Override
    public void save(User user) {
        db.put(user.getId(), user);
    }

    @Override
    public java.util.Collection<User> findAll() {
        return db.values();
    }

    @Override
    public void deleteAll() {
        db.clear();
    }
}

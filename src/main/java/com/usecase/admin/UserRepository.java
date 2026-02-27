package com.usecase.admin;

import com.entity.User;

public interface UserRepository {
    User findById(String id);

    void save(User user);

    java.util.Collection<User> findAll();

    void deleteAll();
}

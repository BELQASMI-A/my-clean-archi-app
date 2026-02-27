package com.infrastructure.persistence;

import com.entity.User;
import com.entity.UserImp;
import com.usecase.admin.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptateur de Persistance PostgreSQL (Couche Verte / Interface Adapter)
 * Cette classe fait le pont entre le Port domaine et la technologie JPA.
 */
@Component
@Primary // On indique à Spring d'utiliser celui-ci à la place de l'InMemory
public class PostgresUserRepository implements UserRepository {

    private final SpringDataUserRepository jpaRepository;

    public PostgresUserRepository(SpringDataUserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User findById(String id) {
        return jpaRepository.findById(id)
                .map(entity -> new UserImp(entity.getId(), entity.getLabel()))
                .orElse(null);
    }

    @Override
    public void save(User user) {
        // On transforme l'Entité Métier en Entité JPA (Mappage)
        JpaUserEntity entity = new JpaUserEntity(user.getId(), user.getLabel());
        jpaRepository.save(entity);
    }

    // Utilisé par le contrôleur pour l'affichage
    public java.util.Collection<User> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> new UserImp(entity.getId(), entity.getLabel()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }
}

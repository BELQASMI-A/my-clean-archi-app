package com.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TEST DE PERSISTANCE (INFRASTRUCTURE)
 * On vérifie que le mapping JPA vers la base de données (H2 ici) est correct.
 */
@DataJpaTest
class SpringDataUserRepositoryTest {

    @Autowired
    private SpringDataUserRepository repository;

    @Test
    void should_save_and_retrieve_user_entity() {
        // GIVEN
        JpaUserEntity entity = new JpaUserEntity("U99", "Database User");

        // WHEN
        repository.save(entity);
        Optional<JpaUserEntity> found = repository.findById("U99");

        // THEN
        assertTrue(found.isPresent());
        assertEquals("Database User", found.get().getLabel());
    }

    @Test
    void should_not_find_non_existing_user() {
        // WHEN
        Optional<JpaUserEntity> found = repository.findById("VOID");

        // THEN
        assertFalse(found.isPresent());
    }
}

package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MageRepositoryTest {
    private MageRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MageRepository();
    }

    @Test
    void saveAndGetMage() {
        Mage mage = new Mage("Gandalf", 10);
        repository.save(mage);
        assertEquals(mage, repository.find("Gandalf").orElse(null));
    }

    @Test
    void deleteMage() {
        Mage mage = new Mage("Gandalf", 10);
        repository.save(mage);
        repository.delete("Gandalf");
        assertTrue(repository.find("Gandalf").isEmpty());
    }
    @Test
    void findNonExistingMageReturnsEmpty() {
        Optional<Mage> result = repository.find("Radagast");
        assertFalse(result.isPresent(), "Powinno zwrócić Optional.empty() dla nieistniejącego maga");
    }

    @Test
    void saveExistingMageThrowsException() {
        repository.save(new Mage("Gandalf", 10));
        assertThrows(IllegalArgumentException.class, () -> repository.save(new Mage("Gandalf", 10)));
    }

    @Test
    void deleteNonExistingMageThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> repository.delete("Radagast"));
    }

}


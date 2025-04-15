package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MageControllerTest {

    @Mock
    private MageRepository mageRepository;

    @InjectMocks
    private MageController mageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findMageThatExists() {
        when(mageRepository.find("Gandalf")).thenReturn(Optional.of(new Mage("Gandalf", 10)));

        String result = mageController.find("Gandalf");

        assertEquals("Found: Gandalf, level: 10", result);
    }

    @Test
    void findMageThatDoesNotExist() {
        when(mageRepository.find("Saruman")).thenReturn(Optional.empty());

        String result = mageController.find("Saruman");

        assertEquals("not found", result);
    }

    @Test
    void testFindMageWhenExists() {
        // Konfiguracja mocka
        when(mageRepository.find("Gandalf")).thenReturn(Optional.of(new Mage("Gandalf", 10)));

        // Wywołanie metody kontrolera
        String result = mageController.find("Gandalf");

        // Weryfikacja wyniku
        assertEquals("Found: Gandalf, level: 10", result);

        // Weryfikacja, czy odpowiednia metoda repozytorium została wywołana
        verify(mageRepository).find("Gandalf");
    }


    @Test
    void saveNewMageReturnsDone() {
        String result = mageController.save("Gandalf", 10);
        assertEquals("done", result);
        verify(mageRepository).save(any(Mage.class));
    }




    @Test
    void saveExistingMageThrowsExceptionAndReturnsBadRequest() {
        doThrow(new IllegalArgumentException("Mage already exists")).when(mageRepository).save(any(Mage.class));
        String result = mageController.save("Gandalf", 10);

        assertEquals("bad request", result);
    }



    @Test
    void deleteNonExistingMageReturnsNotFound() {
        doThrow(new IllegalArgumentException()).when(mageRepository).delete("Radagast");
        String result = mageController.delete("Radagast");
        assertEquals("not found", result);
    }
}

package enerisan.incident.model;

import enerisan.incident.repository.IncidentRepository;
import enerisan.incident.service.IncidentService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Incident class.
 * Covers basic POJO functionality, default values, and simple business rules.
 * Validator not used; relies on Spring Boot Bean Validation in production for @NotNull and @Size.
 */
class IncidentTest {

    @Mock
    private IncidentRepository incidentRepository;

    @InjectMocks
    private IncidentService incidentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    // Test getters and setters

    @Test
    void testAllSettersAndGetters() {
        Incident incident = new Incident();
        incident.setId(123);
        incident.setTitle("Poubelle débordante");
        incident.setAddress("Rue Mignet");
        incident.setPostalCode("13100");
        incident.setNeighborhood("Centre-ville");
        incident.setImage("image.png");
        incident.setDescription("La poubelle déborde depuis 1 semaine");


        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime closedAt = LocalDateTime.now().plusDays(1);
        incident.setCreatedAt(createdAt);
        incident.setClosedAt(closedAt);


        BigDecimal latitude = new BigDecimal("43.529742");
        BigDecimal longitude = new BigDecimal("5.447427");
        incident.setLatitude(latitude);
        incident.setLongitude(longitude);


        Status status = new Status();
        status.setType("signalé");
        status.setId(1);
        incident.setStatus(status);

        City city = new City();
        city.setName("Aix-en-Provence");
        incident.setCity(city);

        User user = new User();
        user.setFirstname("Lois");
        incident.setUser(user);

        // Assertions
        assertEquals(123, incident.getId());
        assertEquals("Poubelle débordante", incident.getTitle());
        assertEquals("Rue Mignet", incident.getAddress());
        assertEquals("13100", incident.getPostalCode());
        assertEquals("Centre-ville", incident.getNeighborhood());
        assertEquals("image.png", incident.getImage());
        assertEquals("La poubelle déborde depuis 1 semaine", incident.getDescription());

        assertEquals(createdAt, incident.getCreatedAt());
        assertEquals(closedAt, incident.getClosedAt());
        assertEquals(latitude, incident.getLatitude());
        assertEquals(longitude, incident.getLongitude());

        assertEquals(status, incident.getStatus());
        assertEquals("Aix-en-Provence", incident.getCity().getName());
        assertEquals("Lois", incident.getUser().getFirstname());
    }


    // Test default value for createdAt

    @Test
    void testCreatedAtDefaultIsNotNull() {
        // Default createdAt should be initialized
        Incident incident = new Incident();
        assertNotNull(incident.getCreatedAt(), "createdAt should be initialized by default");
    }


    // Test simple business rule:
    // Incident can only be modified if status = "signalé"

    @Test
    void updateIncident_shouldFail_whenStatusIsNotSignale() {
        // Arrange
        Incident existingIncident = new Incident();
        existingIncident.setId(1);
        Status status = new Status();
        status.setType("validé"); // Not "signalé"
        existingIncident.setStatus(status);

        Incident updateRequest = new Incident();
        updateRequest.setTitle("Tentative de modification d'un incident");

        // Mock the repository to return the existing incident
        when(incidentRepository.findById(1)).thenReturn(Optional.of(existingIncident));

        // Act & Assert: exception should be thrown
        assertThrows(IllegalStateException.class,
                () -> incidentService.updateIncident(updateRequest, 1));

        // Verify that save() is never called
        verify(incidentRepository, never()).save(any());
    }



    //Test validations

    @Test
    void incidentShouldPassValidationWhenAllFieldsValid() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        City city = new City();
        city.setId(1);
        city.setName("Aix-en-Provence");


        User user = new User();
        user.setId(1);
        user.setFirstname("Lois");
        user.setLastname("Lane");
        user.setPhone("0123456789");
        user.setEmail("loislane@gmail.com");


        Status status = new Status();
        status.setId(1);
        status.setType("signalé");


        Incident incident = new Incident();
        incident.setCity(city);
        incident.setUser(user);
        incident.setStatus(status);
        incident.setTitle("");
        incident.setAddress("123 Rue Mignet");
        incident.setPostalCode("13100");

        Set<ConstraintViolation<Incident>> violations = validator.validate(incident);

        assertTrue(violations.isEmpty(),
                () -> "Expected no violations, but got: " + violations);
    }

}








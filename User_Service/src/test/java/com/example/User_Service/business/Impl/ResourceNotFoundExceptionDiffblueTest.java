package com.example.User_Service.business.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionDiffblueTest {

    @Test
    @DisplayName("Test new ResourceNotFoundException(String)")
    @Tag("MaintainedByDiffblue")
    void testNewResourceNotFoundException() {
        // Arrange and Act
        ResourceNotFoundException actualResourceNotFoundException = new ResourceNotFoundException("Msg");

        // Assert
        assertEquals("Msg", actualResourceNotFoundException.getMessage());
        assertNull(actualResourceNotFoundException.getCause());
        assertEquals(0, actualResourceNotFoundException.getSuppressed().length);
    }
}

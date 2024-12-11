import org.example.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса {@link Subdivision}.
 */
class SubdivisionTest {

    @Test
    void testSubdivisionCreation() {
        // Arrange & Act
        Subdivision subdivision = new Subdivision("001", "Finance");

        // Assert
        assertEquals("001", subdivision.getId(), "ID подразделения должен быть '001'");
        assertEquals("Finance", subdivision.getName(), "Название подразделения должно быть 'Finance'");
    }

    @Test
    void testToString() {
        // Arrange
        Subdivision subdivision = new Subdivision("002", "Marketing");

        // Act
        String result = subdivision.toString();

        // Assert
        String expected = "Subdivision{id='002', name='Marketing'}";
        assertEquals(expected, result, "Метод toString() вернул неверное представление");
    }
}

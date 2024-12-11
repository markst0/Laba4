import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса {@link Person}.
 */
class PersonTest {

    @Test
    void testPersonCreation() {
        // Arrange
        Subdivision subdivision = new Subdivision("001", "IT");

        // Act
        Person person = new Person(1, "Alice", "Female", subdivision, 5000.0, "12.05.1990");

        // Assert
        assertEquals(1, person.getId(), "ID должен быть равен 1");
        assertEquals("Alice", person.getName(), "Имя должно быть 'Alice'");
        assertEquals("Female", person.getGender(), "Пол должен быть 'Female'");
        assertEquals("IT", person.getSubdivision().getName(), "Название подразделения должно быть 'IT'");
        assertEquals(5000.0, person.getSalary(), 0.01, "Зарплата должна быть равна 5000.0");
        assertEquals("12.05.1990", person.getBirthDate(), "Дата рождения должна быть '12.05.1990'");
    }

    @Test
    void testToString() {
        // Arrange
        Subdivision subdivision = new Subdivision("002", "HR");
        Person person = new Person(2, "Bob", "Male", subdivision, 4500.5, "01.01.1985");

        // Act
        String result = person.toString();

        // Assert
        String expected = "Person{id=2, name='Bob', gender='Male', subdivision=Subdivision{id='002', name='HR'}, salary=4500.50, birthDate='01.01.1985'}";
        assertEquals(expected, result, "Метод toString() вернул неверное представление");
    }
}

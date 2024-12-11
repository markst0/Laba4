import org.example.Main;
import org.example.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    public static final String TEST_CSV_FILE = "test_people.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Создаем временный CSV-файл для тестов
        File file = new File(TEST_CSV_FILE);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("id;name;gender;BirthDate;Division;Salary\n"); // Заголовок
            writer.write("1;Alice;Female;12.05.1985;HR;3000.00\n");
            writer.write("2;Bob;Male;15.06.1990;IT;5000.50\n");
            writer.write("3;Charlie;Male;01.01.1975;Finance;4000.00\n");
            writer.write("4;Invalid;Female;07.07.2000;HR;abc\n"); // Некорректное значение зарплаты
        }
    }

    @Test
    void testReadPeopleFromCSV_ValidData() {
        // Проверяем, что метод правильно считывает корректные строки
        List<Person> people = Main.readPeopleFromCSV(TEST_CSV_FILE, ';');
        assertEquals(3, people.size(), "Ожидается 3 человека в списке (строки с корректными данными)");

        // Проверяем данные первого человека
        Person person = people.getFirst();
        assertEquals(1, person.getId());
        assertEquals("Alice", person.getName());
        assertEquals("Female", person.getGender());
        assertEquals("HR", person.getSubdivision().getName());
        assertEquals(3000.00, person.getSalary(), 0.01);
        assertEquals("12.05.1985", person.getBirthDate());
    }

    @Test
    void testReadPeopleFromCSV_InvalidSalary() {
        // Проверяем, что строки с некорректной зарплатой пропускаются
        List<Person> people = Main.readPeopleFromCSV(TEST_CSV_FILE, ';');
        assertTrue(people.stream().noneMatch(person -> "Invalid".equals(person.getName())),
                "Строка с некорректной зарплатой должна быть пропущена");
    }

    @Test
    void testReadPeopleFromCSV_EmptyFile() throws IOException {
        // Создаем пустой CSV-файл
        File emptyFile = new File("empty.csv");
        try (FileWriter writer = new FileWriter(emptyFile)) {
            writer.write(""); // Пустое содержимое
        }

        // Проверяем, что список пуст для пустого файла
        List<Person> people = Main.readPeopleFromCSV("empty.csv", ';');
        assertTrue(people.isEmpty(), "Для пустого файла список должен быть пустым");
    }
}

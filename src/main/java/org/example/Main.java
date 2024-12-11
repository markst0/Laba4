package org.example;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Главный класс программы для чтения данных из CSV-файла и преобразования их в список объектов.
 */
public class Main {
    /**
     * Логгер для записи информации о выполнении программы.
     */
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        String csvFilePath = "foreign_names.csv"; // файл должен быть в src/main/resources
        char separator = ';';

        // Чтение людей из CSV и вывод их на экран
        List<Person> people = readPeopleFromCSV(csvFilePath, separator);

        if (!people.isEmpty()) {
            people.forEach(System.out::println);
        } else {
            LOGGER.warning("Список людей пуст. Возможно, файл CSV пустой или содержит ошибки.");
        }
    }

    /**
     * Считывает данные из CSV-файла, преобразует строки в объекты {@link Person},
     * связывает их с объектами {@link Subdivision}, и возвращает список людей.
     *
     * @param csvFilePath путь к CSV-файлу (должен находиться в ресурсах проекта).
     * @param separator   символ-разделитель (например, ';').
     * @return список объектов {@link Person}.
     */
    public static List<Person> readPeopleFromCSV(String csvFilePath, char separator) {
        Map<String, Subdivision> subdivisions = new HashMap<>();
        List<Person> people = new ArrayList<>();

        try (InputStream in = Main.class.getClassLoader().getResourceAsStream(csvFilePath);
             InputStreamReader inputStreamReader = in == null ? null : new InputStreamReader(in)) {
            if (inputStreamReader == null) {
                throw new FileNotFoundException("Файл не найден: " + csvFilePath);
            }

            // Создаем CSVReader с кастомным разделителем
            CSVReader reader = new CSVReaderBuilder(inputStreamReader)
                    .withCSVParser(new CSVParserBuilder().withSeparator(separator).build())
                    .build();

            // Считываем все строки из CSV
            List<String[]> rows = reader.readAll();
            rows.remove(0); // Удаляем заголовок

            for (String[] row : rows) {
                try {
                    // Преобразуем строку в объект Person
                    int id = Integer.parseInt(row[0]);
                    String name = row[1];
                    String gender = row[2];
                    String birthDate = row[3];
                    String subdivisionName = row[4];
                    String salaryString = row[5];

                    if (!isValidDouble(salaryString)) {
                        LOGGER.log(Level.WARNING, "Некорректное значение зарплаты в строке: " + Arrays.toString(row));
                        continue;
                    }

                    double salary = Double.parseDouble(salaryString);

                    // Создаем или получаем существующее подразделение
                    Subdivision subdivision = subdivisions.computeIfAbsent(subdivisionName, nameKey ->
                            new Subdivision(UUID.randomUUID().toString(), nameKey));

                    // Добавляем нового человека в список
                    people.add(new Person(id, name, gender, subdivision, salary, birthDate));
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.WARNING, "Ошибка преобразования данных в строке: " + Arrays.toString(row), e);
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING, "Ошибка обработки строки: " + Arrays.toString(row), e);
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "CSV файл не найден: " + csvFilePath, e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка при чтении файла CSV", e);
        }

        return people;
    }

    /**
     * Проверяет, является ли строка допустимым числом с плавающей точкой.
     *
     * @param str строка для проверки.
     * @return true, если строка является числом; false в противном случае.
     */
    private static boolean isValidDouble(String str) {
        Pattern doublePattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return doublePattern.matcher(str).matches();
    }
}
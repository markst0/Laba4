package org.example;
import java.util.Locale;

/**
 * Класс представляет человека с информацией о его данных, зарплате и подразделении.
 */
public class Person {
    private final int id;
    private final String name;
    private final String gender;
    private final Subdivision subdivision;
    private final double salary;
    private final String birthDate;

    /**
     * Конструктор для создания объекта {@link Person}.
     *
     * @param id          уникальный идентификатор человека.
     * @param name        имя человека.
     * @param gender      пол человека (Male/Female).
     * @param subdivision подразделение, к которому относится человек.
     * @param salary      зарплата человека.
     * @param birthDate   дата рождения человека в формате dd.MM.yyyy.
     */
    public Person(int id, String name, String gender, Subdivision subdivision, double salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.subdivision = subdivision;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * Возвращает уникальный идентификатор человека.
     *
     * @return идентификатор человека.
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает имя человека.
     *
     * @return имя человека.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает пол человека.
     *
     * @return пол человека (Male/Female).
     */
    public String getGender() {
        return gender;
    }

    /**
     * Возвращает подразделение, к которому относится человек.
     *
     * @return объект {@link Subdivision}, представляющий подразделение.
     */
    public Subdivision getSubdivision() {
        return subdivision;
    }

    /**
     * Возвращает зарплату человека.
     *
     * @return зарплата человека.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Возвращает дату рождения человека.
     *
     * @return дата рождения в формате dd.MM.yyyy.
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Возвращает строковое представление объекта {@link Person}.
     *
     * @return строка с информацией о человеке.
     */
    @Override
    public String toString() {
        return String.format(Locale.US, "Person{id=%d, name='%s', gender='%s', subdivision=%s, salary=%.2f, birthDate='%s'}",
                id, name, gender, subdivision, salary, birthDate);
    }

}

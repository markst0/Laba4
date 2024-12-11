package org.example;

/**
 * Класс представляет подразделение, к которому относится человек.
 */
public class Subdivision {
    private final String id;
    private final String name;

    /**
     * Конструктор для создания объекта {@link Subdivision}.
     *
     * @param id   уникальный идентификатор подразделения.
     * @param name название подразделения.
     */
    public Subdivision(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает уникальный идентификатор подразделения.
     *
     * @return идентификатор подразделения.
     */
    public String getId() {
        return id;
    }

    /**
     * Возвращает название подразделения.
     *
     * @return название подразделения.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает строковое представление объекта {@link Subdivision}.
     *
     * @return строка с информацией о подразделении.
     */
    @Override
    public String toString() {
        return String.format("Subdivision{id='%s', name='%s'}", id, name);
    }
}

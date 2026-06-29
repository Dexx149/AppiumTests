package models;

import net.datafaker.Faker;

import java.util.Arrays;

public enum NewsCategory {
    ANNOUNCEMENT(1, "Объявление"),
    BIRTHDAY(2, "День рождения"),
    SALARY(3, "Зарплата"),
    UNION(4,"Профсоюз"),
    HOLIDAY(5,"Праздник"),
    MASSAGE(6,"Массаж"),
    GRATITUDE(7,"Благодарность"),
    HELP(8,"Нужна помощь");

    private final int id;
    private final String displayName;

    NewsCategory(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static NewsCategory fromId(int id) {
        return Arrays.stream(values())
                .filter(category -> category.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неизвестная категория с id: " + id));
    }
}
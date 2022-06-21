package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final List<Meal> mealsForUser = Stream.of(
                    new Meal(START_SEQ + 3, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                    new Meal(START_SEQ + 4, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                    new Meal(START_SEQ + 5, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                    new Meal(START_SEQ + 6, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                    new Meal(START_SEQ + 7, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                    new Meal(START_SEQ + 8, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                    new Meal(START_SEQ + 9, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
            )
            .sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());

    public static final List<Meal> mealsForAdmin = Stream.of(
                    new Meal(START_SEQ + 10, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510),
                    new Meal(START_SEQ + 11, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500)
            )
            .sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());

    public static final Meal someMealForUser = mealsForUser.get(1);
    public static final Meal someMealForAdmin = mealsForAdmin.get(1);
    public static final LocalDateTime duplicatedDTForAdmin = LocalDateTime.of(2015, Month.JUNE, 1, 14, 0);

    public static final int SOME_MEAL_ID_FOR_USER = someMealForUser.getId();
    public static final int SOME_MEAL_ID_FOR_ADMIN = someMealForAdmin.getId();
    public static final int NOT_FOUND_MEAL_ID = START_SEQ + 14;


    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.MAY, 1, 18, 0), "new meal", 800);
    }

    public static Meal getUpdatedForUser() {
        Meal updated = new Meal(someMealForUser);
        updated.setDescription("updated");
        updated.setDateTime(LocalDateTime.of(2022, Month.JUNE, 20, 15, 16));
        updated.setCalories(888);
        return updated;
    }

    public static Meal getUpdatedForAdmin() {
        Meal updated = new Meal(someMealForAdmin);
        updated.setDescription("updated");
        updated.setDateTime(LocalDateTime.of(2022, Month.JUNE, 20, 15, 16));
        updated.setCalories(888);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}

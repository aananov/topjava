package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal userMeal1 = new Meal(START_SEQ + 3, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal userMeal2 = new Meal(START_SEQ + 4, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal userMeal3 = new Meal(START_SEQ + 5, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal userMeal4 = new Meal(START_SEQ + 6, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal userMeal5 = new Meal(START_SEQ + 7, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal userMeal6 = new Meal(START_SEQ + 8, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal userMeal7 = new Meal(START_SEQ + 9, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

    public static final Meal adminMeal1 = new Meal(START_SEQ + 10, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal adminMeal2 = new Meal(START_SEQ + 11, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final List<Meal> userMeals = Arrays.asList(userMeal7, userMeal6, userMeal5, userMeal4, userMeal3, userMeal2, userMeal1);
    public static final List<Meal> adminMeals = Arrays.asList(adminMeal2,adminMeal1);

    public static final int ADMIN_MEAL2_ID = adminMeal2.getId();
    public static final int USER_MEAL2_ID = userMeal2.getId();
    public static final int NOT_FOUND_MEAL_ID = START_SEQ + 14;

    public static final LocalDateTime DUPLICATED_DT_FOR_ADMIN = adminMeal2.getDateTime();
    public static final LocalDate START = LocalDate.of(2020, Month.JANUARY, 29);
    public static final LocalDate END = LocalDate.of(2020, Month.JANUARY, 30);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.MAY, 1, 18, 0), "new meal", 800);
    }

    public static Meal getUpdatedUser() {
        Meal updated = new Meal(userMeal2);
        updated.setDescription("updated");
        updated.setDateTime(LocalDateTime.of(2022, Month.JUNE, 20, 15, 16));
        updated.setCalories(888);
        return updated;
    }

    public static Meal getUpdatedAdmin() {
        Meal updated = new Meal(adminMeal1);
        updated.setDescription("updated");
        updated.setDateTime(LocalDateTime.of(2022, Month.JUNE, 20, 15, 16));
        updated.setCalories(888);
        return updated;
    }

    public static List<Meal> getBetweenInclusiveUser() {
        return Arrays.asList(userMeal3, userMeal2, userMeal1);
    }

    public static List<Meal> getBetweenInclusiveAdmin() {
        return Arrays.asList(adminMeal2,adminMeal1);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}

package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.Collections;
import java.util.List;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void getWithMeal() {
        User userWithMeals = service.getWithMeal(UserTestData.USER_ID);
        List<Meal> userMeals = userWithMeals.getMeals();
        UserTestData.USER_MATCHER.assertMatch(userWithMeals, UserTestData.user);
        MealTestData.MEAL_MATCHER.assertMatch(userMeals, MealTestData.meals);
    }

    @Test
    public void getWithNullMeals() {
        User userWithNullMeals = service.getWithMeal(UserTestData.GUEST_ID);
        System.out.println(userWithNullMeals);
        List<Meal> userMeals = userWithNullMeals.getMeals();
        UserTestData.USER_MATCHER.assertMatch(userWithNullMeals, UserTestData.guest);
        MealTestData.MEAL_MATCHER.assertMatch(userMeals, Collections.EMPTY_LIST);
    }
}

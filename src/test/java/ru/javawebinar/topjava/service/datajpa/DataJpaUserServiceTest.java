package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.List;

@ActiveProfiles("datajpa")
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void getWithMeal() {
        User userWithMeals = service.getWithMeal(UserTestData.USER_ID);
        List<Meal> userMeals = userWithMeals.getMeals();
        UserTestData.USER_MATCHER.assertMatch(userWithMeals, UserTestData.user);
        MealTestData.MEAL_MATCHER.assertMatch(userMeals, MealTestData.meals);
    }
}

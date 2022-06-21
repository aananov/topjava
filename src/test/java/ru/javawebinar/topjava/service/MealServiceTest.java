package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.Util;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(SOME_MEAL_ID_FOR_USER, USER_ID);
        assertMatch(meal, someMealForUser);
    }

    @Test
    public void getNotFoundMeal() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_MEAL_ID, USER_ID));
    }

    @Test
    public void createDuplicateDateMeal() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, duplicatedDTForAdmin, "duplicate date", 500), ADMIN_ID));
    }

    @Test
    public void getOtherUserMeal() {
        assertThrows(NotFoundException.class, () -> service.get(SOME_MEAL_ID_FOR_USER, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(SOME_MEAL_ID_FOR_USER, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(SOME_MEAL_ID_FOR_USER, USER_ID));
    }

    @Test
    public void deleteOtherUserMeal() {
        assertThrows(NotFoundException.class, () -> service.delete(SOME_MEAL_ID_FOR_ADMIN, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        LocalDate start = LocalDate.of(2020, Month.JANUARY, 29);
        LocalDate end = LocalDate.of(2020, Month.JANUARY, 31);
        List<Meal> actual = service.getBetweenInclusive(start, end, USER_ID);
        List<Meal> expected = mealsForUser.stream()
                .filter(meal -> Util.isBetweenHalfOpen(meal.getDateTime(),
                        DateTimeUtil.atStartOfDayOrMin(start),DateTimeUtil.atStartOfNextDayOrMax(end)))
                .collect(Collectors.toList());
        assertMatch(actual,expected);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, mealsForUser);
    }

    @Test
    public void update() {
        Meal updated = getUpdatedForUser();
        service.update(updated, USER_ID);
        assertMatch(service.get(SOME_MEAL_ID_FOR_USER, USER_ID), updated);
    }

    @Test
    public void updateOtherUserMeal() {
        Meal updated = getUpdatedForAdmin();
        assertThrows(NotFoundException.class, () -> service.update(updated, USER_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }
}
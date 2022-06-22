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
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

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
        Meal meal = service.get(USER_MEAL2_ID, USER_ID);
        assertMatch(meal, userMeal2);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_MEAL_ID, USER_ID));
    }

    @Test
    public void createDuplicateDate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, DUPLICATED_DT_FOR_ADMIN, "duplicate date", 500), ADMIN_ID));
    }

    @Test
    public void getOtherUser() {
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL2_ID, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL2_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL2_ID, USER_ID));
    }

    @Test
    public void deleteOtherUser() {
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL2_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_MEAL_ID, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> actual = service.getBetweenInclusive(START, END, USER_ID);
        assertMatch(actual,getBetweenInclusiveUser());
    }

    @Test
    public void getBetweenInclusiveNull() {
        List<Meal> actual = service.getBetweenInclusive(null,null,ADMIN_ID);
        assertMatch(actual,getBetweenInclusiveAdmin());
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, userMeals);
    }

    @Test
    public void update() {
        Meal updated = getUpdatedUser();
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL2_ID, USER_ID), getUpdatedUser());
    }

    @Test
    public void updateOtherUser() {
        Meal updated = getUpdatedAdmin();
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
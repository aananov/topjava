package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoMemoryImpl implements MealDao {

    private static final AtomicInteger ID = new AtomicInteger(0);

    private final List<Meal> meals;


    {
        meals = new CopyOnWriteArrayList<>();
        meals.add(new Meal(ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public Meal getById(Integer id) {
        return meals.stream().filter(meal -> meal.getId().equals(id)).findAny().get();
    }

    @Override
    public void delete(Meal meal) {
        meals.remove(meal);
    }

    @Override
    public void update(Meal mealToUpdate, LocalDateTime updatedDateTime, String updatedDescription, int updatedCalories) {
        if (!mealToUpdate.getDateTime().equals(updatedDateTime)) mealToUpdate.setDateTime(updatedDateTime);
        if (!mealToUpdate.getDescription().equals(updatedDescription)) mealToUpdate.setDescription(updatedDescription);
        if (mealToUpdate.getCalories() != updatedCalories) mealToUpdate.setCalories(updatedCalories);
    }

    @Override
    public void create(LocalDateTime dateTime, String description, int calories) {
        meals.add(new Meal(ID.incrementAndGet(), dateTime, description, calories));
    }
}

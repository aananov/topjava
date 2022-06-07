package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDao {

    List<Meal> getAll();

    Meal getById(Integer id);

    void delete(Meal meal);

    void update(Meal mealToUpdate, LocalDateTime updatedDateTime, String updatedDescription, int updatedCalories);

    void create(LocalDateTime dateTime, String description, int calories);


}
